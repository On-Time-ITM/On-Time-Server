package org.itm.ontime.application.auth.exception.common

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exceptions that occur when the token is invalid")
class InvalidTokenException(
    @Schema(description = "Token that caused the error", example = "eyJhbGciOiJIUzI1...")
    val token: String
) : BusinessException(
    AuthErrorCode.INVALID_TOKEN,
    "Invalid token: ${token.take(10)}"
)