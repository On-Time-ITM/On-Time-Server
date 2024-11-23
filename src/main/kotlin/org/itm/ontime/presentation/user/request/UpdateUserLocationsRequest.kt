package org.itm.ontime.presentation.user.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.itm.ontime.domain.common.Location
import java.util.*

@Schema(description = "Request to update user locations")
data class UpdateUserLocationsRequest (
    @Schema(
        description = "Meeting ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val meetingId: UUID,

    @Schema(
        description = "User arrival locations",
        example = "[{\"userLocationId\": \"123e4567-e89b-12d3-a456-426614174000\", \"location\": {\"latitude\": 37.7749, \"longitude\": -122.4194}}]"
    )
    @field:NotBlank
    val locations: List<HashMap<UUID, Location>>
)