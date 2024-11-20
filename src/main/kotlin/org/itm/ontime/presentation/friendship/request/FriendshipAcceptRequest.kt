package org.itm.ontime.presentation.friendship.request

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Request DTO for accepting a friend request")
data class FriendshipAcceptRequest(
    @Schema(
        description = "ID of the friendship to accept",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val friendshipId: UUID,

    @Schema(
        description = "ID of the user accepting the friend request",
        example = "987fcdeb-51d2-3456-bcde-789012345678"
    )
    val receiverId: UUID
)
