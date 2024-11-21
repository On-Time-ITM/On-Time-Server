package org.itm.ontime.application.friendship.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when attempting unauthorized actions on a friendship")
class UnauthorizedFriendshipException(
    @Schema(description = "ID of the user attempting the action", example = "123")
    private val receiverId: UUID,
    @Schema(description = "ID of the friendship being accessed", example = "456")
    private val friendshipId: UUID
) : BusinessException(
    FriendshipErrorCode.UNAUTHORIZED_FRIENDSHIP_ACTION,
    "User $receiverId is not authorized to perform this action on friendship $friendshipId"
)