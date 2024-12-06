package org.itm.ontime.presentation.dto.request.participant

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationInfo
import java.util.*

@Schema(description = "Request to update participant locations")
data class ParticipantLocationsRequest (
    @Schema(
        description = "Meeting ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val meetingId: UUID,

    @Schema(
        description = "Locations of participants in the meeting",
        example = "[{\"participantId\": \"123e4567-e89b-12d3-a456-426614174000\", \"location\": {\"latitude\": 37.7749, \"longitude\": -122.4194}}]"
    )
    @field:NotBlank
    val participantLocationInfos : List<ParticipantLocationInfo>
)