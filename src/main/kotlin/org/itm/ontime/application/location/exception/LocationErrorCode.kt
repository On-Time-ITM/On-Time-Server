package org.itm.ontime.application.location.exception

import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class LocationErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    USER_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "L001", "User location not found"),
}