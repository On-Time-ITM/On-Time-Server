package org.itm.ontime.infrastructure.external.qrCode.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when QR code already exists")
class QRCodeAlreadyExistsException(
    @Schema(description = "Meeting ID for which the QR code already exists", example = "550e8400-e29b-41d4-a716-446655440000")
    meetingId: UUID
) : BusinessException(
    QRCodeErrorCode.QR_CODE_ALREADY_EXISTS,
    "The QR code for meeting $meetingId already exists"
)