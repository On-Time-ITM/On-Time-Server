package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when a user attempts to send a friend request to themselves")
class SelfFriendRequestException(
    @Schema(description = "ID of the user attempting the self-request", example = "123")
    private val userId: UUID
) : BusinessException(
    FriendshipErrorCode.SELF_FRIEND_REQUEST,
    "Cannot send friend request to yourself: $userId"
)