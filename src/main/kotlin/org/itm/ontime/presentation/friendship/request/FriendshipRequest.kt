package org.itm.ontime.presentation.friendship.request

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Request DTO for sending a friend request")
data class FriendshipRequest(
    @Schema(
        description = "ID of the user sending the friend request",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val requesterId: UUID,

    @Schema(
        description = "ID of the user receiving the friend request",
        example = "987fcdeb-51d2-3456-bcde-789012345678"
    )
    val receiverId: UUID
)