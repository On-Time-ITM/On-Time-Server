package org.itm.ontime.application.exception.auth

import org.itm.ontime.application.exception.common.ErrorCode
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
    USER_ALREADY_LOGOUT(HttpStatus.BAD_REQUEST, "A007", "User already logged out"),

}