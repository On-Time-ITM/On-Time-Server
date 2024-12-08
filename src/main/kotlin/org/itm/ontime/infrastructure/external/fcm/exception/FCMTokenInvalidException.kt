package org.itm.ontime.infrastructure.external.qrCode.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import org.itm.ontime.infrastructure.external.fcm.exception.FCMErrorCode

@Schema(description = "Exception thrown when token format is invalid")
class FCMTokenInvalidException(
    @Schema(description = "Token that format was not invalid.", example = "fe23dkfks34l909sfde0")
    private val token: String
) : BusinessException(
    FCMErrorCode.FCM_TOKEN_INVALID,
    "Token format of token $token is invalid"
)

