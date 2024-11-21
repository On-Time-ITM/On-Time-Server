package org.itm.ontime.application.user.exception

import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "A005", "User not found"),
}