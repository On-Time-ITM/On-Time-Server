package org.itm.ontime.application.exception.auth

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exceptions that occur when the user is already logged out")
class UserAlreadyLogoutException(
    @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val userId: UUID
) : BusinessException(
    AuthErrorCode.USER_ALREADY_LOGOUT,
    "User already logout: $userId"
)