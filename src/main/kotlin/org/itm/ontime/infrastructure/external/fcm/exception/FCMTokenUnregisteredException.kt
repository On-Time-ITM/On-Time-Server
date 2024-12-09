package org.itm.ontime.infrastructure.external.qrCode.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import org.itm.ontime.infrastructure.external.fcm.exception.FCMErrorCode

@Schema(description = "Exception thrown when token is unregistered")
class FCMTokenUnregisteredException(
    @Schema(description = "Token that was not unregistered.", example = "sdfiosdnsifo92ksdfl2392")
    private val token: String
) : BusinessException(
    FCMErrorCode.FCM_TOKEN_UNREGISTERED,
    "FCM token $token is unregistered."
)

