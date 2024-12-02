package org.itm.ontime.domain.meeting.repository

import org.itm.ontime.domain.meeting.entity.meeting.Meeting
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeetingRepository : JpaRepository<Meeting, UUID> {
    fun findAllByParticipantsParticipantId(userId: UUID) : List<Meeting>

}