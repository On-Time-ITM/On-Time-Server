package org.itm.ontime.application.exception.friendship

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

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