package org.itm.ontime.application.exception.friendship

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when a duplicate friend request is detected")
class AlreadyExistsFriendRequestException(
    @Schema(description = "ID of the user sending the request", example = "123")
    private val requesterId: UUID,
    @Schema(description = "ID of the user receiving the request", example = "456")
    private val recipientId: UUID
) : BusinessException(
    FriendshipErrorCode.DUPLICATE_FRIEND_REQUEST,
    "Friend request already exists between users: $requesterId and $recipientId"
)
