package org.itm.ontime.infrastructure.external.fcm.exception

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class FCMErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    FCM_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "F001", "Failed to send push notification"),
    FCM_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "F002", "Invalid FCM token"),
    FCM_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "F003", "FCM token not found"),
}