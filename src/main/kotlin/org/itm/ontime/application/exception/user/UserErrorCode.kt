package org.itm.ontime.application.exception.user

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "User not found"),
    USER_STATISTICS_NOT_FOUND(HttpStatus.NOT_FOUND, "US001", "User statistics not found"),
}