package org.itm.ontime.domain.meeting.repository

import org.itm.ontime.domain.meeting.entity.Meeting
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MeetingRepository : JpaRepository<Meeting, UUID> {
    fun findAllByParticipantsUserId(userId: UUID) : List<Meeting>
}