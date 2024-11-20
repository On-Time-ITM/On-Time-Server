package org.itm.ontime.application.user.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Exceptions that occur when the user cannot be found")
class UserNotFoundException(
    @Schema(description = "Phone Number that cannot be found", example = "010-1234-5678")
    private val phoneNumber: String
) : BusinessException(
    ErrorCode.USER_NOT_FOUND,
    "User not found with id: $phoneNumber"
)