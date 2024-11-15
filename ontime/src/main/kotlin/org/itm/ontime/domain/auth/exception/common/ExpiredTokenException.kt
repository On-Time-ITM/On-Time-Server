package org.itm.ontime.domain.auth.exception.common

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Exceptions that occur when the token has expired")
class ExpiredTokenException(
    @Schema(description = "Token that has expired", example = "eyJhbGciOiJIUzI1...")
    private val token: String
) : BusinessException(
    ErrorCode.EXPIRED_TOKEN,
    "Expired token: ${token.take(10)}"
){
}