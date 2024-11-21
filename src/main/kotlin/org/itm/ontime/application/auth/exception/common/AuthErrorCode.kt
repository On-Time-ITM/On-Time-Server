package org.itm.ontime.application.auth.exception.common

import org.itm.ontime.global.error.CommonErrorCode
import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class AuthErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "A001", "Unauthorized"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "Invalid token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "Expired token"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A004", "Invalid refresh token"),
    DUPLICATE_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "A005", "Phone number already exists"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "A006", "Invalid password"),

}