package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when a duplicate friend request is detected")
class DuplicateFriendRequestException(
    @Schema(description = "ID of the user sending the request", example = "123")
    private val requesterId: UUID,
    @Schema(description = "ID of the user receiving the request", example = "456")
    private val recipientId: UUID
) : BusinessException(
    ErrorCode.DUPLICATE_FRIEND_REQUEST,
    "Friend request already exists between users: $requesterId and $recipientId"
)
