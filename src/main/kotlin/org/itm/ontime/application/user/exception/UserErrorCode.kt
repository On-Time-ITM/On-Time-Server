package org.itm.ontime.application.user.exception

import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "User not found"),
    USER_LOCATION_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "U002", "User location not found"),
}