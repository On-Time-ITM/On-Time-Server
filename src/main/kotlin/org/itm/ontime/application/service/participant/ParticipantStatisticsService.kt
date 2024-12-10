package org.itm.ontime.application.service.participant

import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.itm.ontime.infrastructure.repository.participant.ParticipantRepository
import org.itm.ontime.presentation.dto.response.user.UserResponse
import org.itm.ontime.presentation.dto.response.user.UserStatisticsResponse
import org.springframework.stereotype.Service
import java.util.*


@Service
class ParticipantStatisticsService(
    private val meetingRepository: MeetingRepository,
    private val participantRepository: ParticipantRepository
) {
    fun getParticipantsStatistics(meetingId: UUID): List<UserResponse> {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow{ MeetingNotFoundException(meetingId) }

        val participants = participantRepository.findAllByMeetingId(meeting.id)

        return participants.map { participant ->
            UserResponse.of(participant.participant)
        }
    }
}