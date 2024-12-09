package org.itm.ontime.infrastructure.external.fcm

import org.itm.ontime.domain.meeting.Meeting
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMTokenNotFoundException
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime


@Component
@EnableScheduling
class Scheduler(
    private val meetingRepository: MeetingRepository,
    private val fcmTokenRepository: FCMTokenRepository,
    private val fcmClient: FCMClient
) {
    @Scheduled(fixedRate = 60000)
    fun checkUpcomingMeetings() {
        val now: LocalDateTime = LocalDateTime.now()

        val upcomingMeetings: List<Meeting> = meetingRepository.findMeetingsForNotification(LocalDateTime.now())

        for (meeting in upcomingMeetings) {
            val timeUntilMeeting: Duration = Duration.between(now, meeting.meetingDateTime)
            val minutesUntil: Long = timeUntilMeeting.toMinutes()

            when (minutesUntil) {
                60L -> sendMeetingNotification(meeting, "1시간 전 알림")
                30L -> sendMeetingNotification(meeting, "30분 전 알림")
                10L -> sendMeetingNotification(meeting, "10분 전 알림")
            }
        }
    }

    private fun sendMeetingNotification(meeting: Meeting, timing: String) {
        val title = "Meeting is Upcoming"
        val body = java.lang.String.format("%s: %s Meeting will start soon.", timing, meeting.name)

        meeting.participants.map { participant ->
            val userId = participant.participant.id
            val token = fcmTokenRepository.findByUserId(userId)
                ?: throw FCMTokenNotFoundException(userId)
            fcmClient.sendNotification(token.token, title, body)
        }
    }

}