package org.itm.ontime.infrastructure.repository.participant

import org.itm.ontime.domain.participant.ParticipantArrival
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ParticipantArrivalRepository : JpaRepository<ParticipantArrival, UUID> {
    fun findByParticipantId(participantId: UUID): ParticipantArrival?
}