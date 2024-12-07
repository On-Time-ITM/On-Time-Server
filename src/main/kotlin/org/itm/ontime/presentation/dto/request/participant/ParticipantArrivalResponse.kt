package org.itm.ontime.presentation.dto.request.participant

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.domain.participant.ParticipantArrival
import org.itm.ontime.domain.participant.ParticipantArrivalStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

@Schema(description = "Arrival information of a participant")
data class ParticipantArrivalResponse(
    @Schema(description = "Meeting ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val meetingId: UUID,

    @Schema(description = "Participant ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val participantId: UUID,

    @Schema(description = "Arrival time of a participant", example = "2022-01-01T10:00:00Z")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val arrivalTime: LocalDateTime?,

    @Schema(description = "Arrival status of a participant", example = "ARRIVED")
    val participantArrivalStatus: ParticipantArrivalStatus,

    @Schema(description = "Is a participant late", example = "false")
    val isLate: Boolean,
) {
    companion object {
        @JvmStatic
        fun of(meetingId: UUID, participantId: UUID, arrival: ParticipantArrival) : ParticipantArrivalResponse =
            ParticipantArrivalResponse(
                meetingId = meetingId,
                participantId = participantId,
                arrivalTime = arrival.arrivedTime,
                participantArrivalStatus = arrival.status,
                isLate = arrival.isLate
            )
    }
}
