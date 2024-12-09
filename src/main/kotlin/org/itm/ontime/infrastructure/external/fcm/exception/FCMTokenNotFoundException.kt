package org.itm.ontime.infrastructure.external.qrCode.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import org.itm.ontime.infrastructure.external.fcm.exception.FCMErrorCode
import java.util.*

@Schema(description = "Exception thrown when token is found")
class FCMTokenNotFoundException(
    @Schema(description = "User id that token was not found.", example = "123e1234-2")
    private val userId: UUID
) : BusinessException(
    FCMErrorCode.FCM_TOKEN_NOT_FOUND,
    "FCM token of user $userId is not found."
)

