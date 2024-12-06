package org.itm.ontime.application.exception.friendship

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when a user attempts to send a friend request to themselves")
class SelfFriendRequestException(
    @Schema(description = "ID of the user attempting the self-request", example = "123")
    private val userId: UUID
) : BusinessException(
    FriendshipErrorCode.SELF_FRIEND_REQUEST,
    "Cannot send friend request to yourself: $userId"
)