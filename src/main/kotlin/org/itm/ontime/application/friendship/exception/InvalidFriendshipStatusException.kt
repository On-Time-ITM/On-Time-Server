package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

class InvalidFriendshipStatusException(
    @Schema(description = "ID of the friendship being accessed", example = "123")
    val friendshipId: UUID,
    @Schema(description = "ID of the user attempting the action", example = "456")
    val receiverId: UUID
) : BusinessException(
    FriendshipErrorCode.INVALID_FRIENDSHIP_STATUS,
    "Cannot modify friendship(id: $friendshipId) with current status for user(id: $receiverId)"
)