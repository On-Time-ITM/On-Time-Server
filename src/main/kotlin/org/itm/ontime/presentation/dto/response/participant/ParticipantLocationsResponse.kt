package org.itm.ontime.presentation.dto.response.participant

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Request to update participant locations")
data class ParticipantLocationsResponse (
    @Schema(
        description = "Meeting ID",
        example = "987fcdeb-51d2-3456-bcde-789012345678"
    )
    @field:NotBlank
    val meetingId: UUID,

    @Schema(
        description = "Location",
        example = "location: {\"latitude\": 37.7749, \"longitude\": -122.4194}"
    )
    @field:NotBlank
    val participantLocationInfos : List<ParticipantLocationInfo>
) {
    companion object {
        @JvmStatic
        fun of(
            meetingId: UUID,
            participantLocationInfos : List<ParticipantLocationInfo>
        ) = ParticipantLocationsResponse(
            meetingId = meetingId,
            participantLocationInfos = participantLocationInfos
        )
    }
}