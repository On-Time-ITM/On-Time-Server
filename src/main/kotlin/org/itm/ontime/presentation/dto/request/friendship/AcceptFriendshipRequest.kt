package org.itm.ontime.presentation.dto.request.friendship

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Request DTO for accepting a friend request")
data class AcceptFriendshipRequest(
    @Schema(
        description = "ID of the friendship to accept",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val friendshipId: UUID,

    @Schema(
        description = "ID of the user that received a friend request",
        example = "987fcdeb-51d2-3456-bcde-789012345678"
    )
    @field:NotBlank
    val receiverId: UUID
)
