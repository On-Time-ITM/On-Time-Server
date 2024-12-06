package org.itm.ontime.presentation.dto.request.meeting

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Request DTO for deleting a meeting")
data class DeleteMeetingRequest(
    @Schema(
        description = "Meeting ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val meetingId: UUID,

    @Schema(
        description = "Host ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val hostId: UUID
)
