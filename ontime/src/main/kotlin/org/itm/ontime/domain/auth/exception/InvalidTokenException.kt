package org.itm.ontime.domain.auth.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Exceptions that occur when the token is invalid")
class InvalidTokenException(
    @Schema(description = "Token that caused the error", example = "eyJhbGciOiJIUzI1...")
    private val token: String
) : BusinessException(
    ErrorCode.INVALID_TOKEN,
    "Invalid token: ${token.take(10)}"
)