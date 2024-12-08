package org.itm.ontime.infrastructure.external.qrCode.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import org.itm.ontime.infrastructure.external.fcm.exception.FCMErrorCode

@Schema(description = "Exception thrown when FCM server is unavailable")
class FCMServerErrorException : BusinessException(
    FCMErrorCode.FCM_SERVER_ERROR,
    "FCM server is unavailable"
)

