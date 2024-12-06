package org.itm.ontime.application.exception.friendship

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

class InvalidFriendshipStatusException(
    @Schema(description = "ID of the friendship being accessed", example = "123")
    val friendshipId: UUID,
    @Schema(description = "ID of the user attempting the action", example = "456")
    val receiverId: UUID
) : BusinessException(
    FriendshipErrorCode.INVALID_FRIENDSHIP_STATUS,
    "Cannot modify friendship(id: $friendshipId) with current status for user(id: $receiverId)"
)