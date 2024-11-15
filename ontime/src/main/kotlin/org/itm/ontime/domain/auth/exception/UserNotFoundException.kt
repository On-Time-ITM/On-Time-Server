package org.itm.ontime.domain.auth.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Exceptions that occur when the user cannot be found")
class UserNotFoundException(
    @Schema(description = "User Id that cannot be found", example = "123")
    private val userId: Long
) : BusinessException(
    ErrorCode.USER_NOT_FOUND,
    "User not found with id: $userId"
)