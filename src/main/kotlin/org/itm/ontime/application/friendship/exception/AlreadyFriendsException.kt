package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when attempting to friend users who are already friends")
class AlreadyFriendsException(
    @Schema(description = "ID of the first user", example = "123")
    private val userId1: UUID,
    @Schema(description = "ID of the second user", example = "456")
    private val userId2: UUID
) : BusinessException(
    FriendshipErrorCode.ALREADY_FRIENDS,
    "Users $userId1 and $userId2 are already friends"
)