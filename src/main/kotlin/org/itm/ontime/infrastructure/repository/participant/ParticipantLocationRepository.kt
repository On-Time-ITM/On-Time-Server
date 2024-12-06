package org.itm.ontime.infrastructure.repository.participant

import org.itm.ontime.domain.participant.ParticipantLocation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ParticipantLocationRepository : JpaRepository<ParticipantLocation, UUID> {
    fun findByMeetingId(meetingId: UUID): List<ParticipantLocation>
    fun findByParticipantId(participantId: UUID): ParticipantLocation?
}