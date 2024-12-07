package org.itm.ontime.infrastructure.repository.meeting

import org.itm.ontime.domain.meeting.Meeting
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeetingRepository : JpaRepository<Meeting, UUID> {
    fun findAllByParticipantsParticipantId(participantId: UUID) : List<Meeting>

}