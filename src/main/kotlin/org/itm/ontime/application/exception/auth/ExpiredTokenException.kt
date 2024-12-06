package org.itm.ontime.application.exception.auth

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException

@Schema(description = "Exceptions that occur when the token has expired")
class ExpiredTokenException(
    @Schema(description = "Token that has expired", example = "eyJhbGciOiJIUzI1...")
    val token: String
) : BusinessException(
    AuthErrorCode.EXPIRED_TOKEN,
    "Expired token: ${token.take(10)}"
){
}