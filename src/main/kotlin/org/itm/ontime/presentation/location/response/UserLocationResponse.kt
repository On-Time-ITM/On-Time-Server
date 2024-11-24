package org.itm.ontime.presentation.location.response

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.itm.ontime.domain.location.entity.Location
import java.util.*

@Schema(description = "Request to update user locations")
data class UserLocationResponse (
    @Schema(
        description = "User location ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val userLocationId: UUID,

    @Schema(
        description = "User ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val userId: UUID,

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
    val location: Location
) {
    companion object {
        @JvmStatic
        fun of(
            userLocationId: UUID,
            userId: UUID,
            meetingId: UUID,
            location: Location
        ) = UserLocationResponse(
            userLocationId,
            userId,
            meetingId,
            location
        )
    }
}