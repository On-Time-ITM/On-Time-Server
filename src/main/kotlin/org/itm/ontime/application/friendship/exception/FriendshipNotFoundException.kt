package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when a friendship relationship cannot be found")
class FriendshipNotFoundException(
    @Schema(description = "ID of the friendship that cannot be found", example = "456")
    private val friendshipId: UUID
) : BusinessException(
    ErrorCode.FRIENDSHIP_NOT_FOUND,
    "Friendship not found with id: $friendshipId"
)
