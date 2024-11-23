package org.itm.ontime.presentation.friendship.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Request DTO for deleting a friendship")
data class FriendshipDeleteRequest(
    @Schema(
        description = "ID of the user initiating the deletion",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val userId: UUID,

    @Schema(
        description = "ID of the friend to remove",
        example = "987fcdeb-51d2-3456-bcde-789012345678"
    )
    @field:NotBlank
    val friendId: UUID
)
