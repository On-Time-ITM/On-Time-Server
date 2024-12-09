package org.itm.ontime.infrastructure.repository.meeting

import org.itm.ontime.domain.meeting.Meeting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import java.util.*


interface MeetingRepository : JpaRepository<Meeting, UUID> {
    fun findAllByParticipantsParticipantId(participantId: UUID) : List<Meeting>

    @Query("""
        SELECT m FROM Meeting m WHERE 
        m.meetingDateTime > :now AND (
            FUNCTION('TIMESTAMPDIFF', MINUTE, :now, m.meetingDateTime) = 10 OR 
            FUNCTION('TIMESTAMPDIFF', MINUTE, :now, m.meetingDateTime) = 30 OR 
            FUNCTION('TIMESTAMPDIFF', MINUTE, :now, m.meetingDateTime) = 60
        )
    """)
    fun findMeetingsForNotification(now: LocalDateTime?): List<Meeting>

}