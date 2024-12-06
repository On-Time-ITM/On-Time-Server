package org.itm.ontime.infrastructure.repository.participant

import org.itm.ontime.domain.participant.Participant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ParticipantRepository : JpaRepository<Participant, UUID> {
    fun findByMeetingIdAndParticipantId(meetingId: UUID, participantId: UUID): Participant?
    fun findAllByMeetingId(meetingId: UUID): List<Participant>
}