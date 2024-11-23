package org.itm.ontime.presentation.friendship.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@Schema(description = "Response DTO for friend request information")
data class FriendRequestResponse(
    @Schema(description = "ID of the friendship", example = "123e4567-e89b-12d3-a456-426614174000")
    val friendshipId: UUID,

    @Schema(description = "Information about the requester")
    val requester: FriendResponse,

    @Schema(description = "When the friend request was created", example = "2024-01-01T12:00:00")
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(
            friendshipId: UUID,
            requester: FriendResponse,
            createdAt: LocalDateTime
        ): FriendRequestResponse {
            return FriendRequestResponse(friendshipId, requester, createdAt)
        }
    }
}

