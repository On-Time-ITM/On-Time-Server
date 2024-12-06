package org.itm.ontime.application.exception.auth

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException

@Schema(description = "Exceptions that occur when the token is invalid")
class InvalidRefreshTokenException(
    @Schema(description = "Token that caused the error", example = "eyJhbGciOiJIUzI1...")
    val token: String
) : BusinessException(
    AuthErrorCode.INVALID_REFRESH_TOKEN,
    "Invalid token: ${token.take(10)}"
)