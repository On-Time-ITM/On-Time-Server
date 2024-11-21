package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when a friendship relationship cannot be found")
class FriendshipNotFoundException private constructor(
    message: String
) : BusinessException(
    FriendshipErrorCode.FRIENDSHIP_NOT_FOUND,
    message
) {
    companion object {
        @JvmStatic
        fun fromId(
            @Schema(description = "ID of the friendship that cannot be found", example = "123e4567-e89b-12d3-a456-426614174000")
            friendshipId: UUID
        ) = FriendshipNotFoundException("Friendship not found with id: $friendshipId")

        @JvmStatic
        fun fromUserIds(
            @Schema(description = "ID of the first user", example = "123e4567-e89b-12d3-a456-426614174000")
            userId: UUID,
            @Schema(description = "ID of the second user", example = "987fcdeb-51d2-3456-bcde-789012345678")
            friendId: UUID
        ) = FriendshipNotFoundException("Friendship not found between users: $userId and $friendId")
    }
}