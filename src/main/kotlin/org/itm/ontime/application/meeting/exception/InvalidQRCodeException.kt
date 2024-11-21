package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exception thrown when QR code is invalid")
class InvalidQRCodeException(
    @Schema(description = "Invalid QR code", example = "abc123")
    private val qrCode: String
) : BusinessException(
    MeetingErrorCode.INVALID_QR_CODE,
    "Invalid QR code: $qrCode"
)