package org.itm.ontime.domain.meeting.repository

import org.itm.ontime.domain.meeting.entity.meeting.MeetingParticipant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeetingParticipantRepository : JpaRepository<MeetingParticipant, UUID> {
    fun findByMeetingIdAndParticipantId(meetingId: UUID, participantId: UUID): MeetingParticipant?
}