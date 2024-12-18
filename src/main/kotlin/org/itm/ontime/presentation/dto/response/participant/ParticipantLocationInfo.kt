package org.itm.ontime.presentation.dto.response.participant

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.participant.Participant
import java.util.*

@Schema(description = "Participant location information")
data class ParticipantLocationInfo (
    @Schema(
        description = "Participant ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val participantId: UUID,

    @Schema(
        description = "Participant's name",
        example = "saeyeon"
    )
    @field:NotBlank
    val participantName: String,

    @Schema(
        description = "Participant current location",
        example = "[{\"location\": {\"latitude\": 37.7749, \"longitude\": -122.4194}, \"address\": Frontier}]"
    )
    @field:NotNull
    val participantLocation: Location?
) {
    companion object {
        @JvmStatic
        fun of (participantId: UUID, participantName: String, participantLocation: Location) =
            ParticipantLocationInfo(
                participantId = participantId,
                participantName = participantName,
                participantLocation = participantLocation
            )

        @JvmStatic
        fun from(participant: Participant) =
            ParticipantLocationInfo(
                participantId = participant.id,
                participantName = participant.participant.name,
                participantLocation = participant.location
            )
    }
}