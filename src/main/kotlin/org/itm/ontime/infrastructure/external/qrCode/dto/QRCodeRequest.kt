package org.itm.ontime.infrastructure.external.qrCode.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Request object for QR code creation")
data class QRCodeRequest(
    @Schema(description = "Meeting ID for which the QR code is requested", example = "550e8400-e29b-41d4-a716-446655440000")
    @field:NotBlank
    val meetingId: UUID,

    @Schema(description = "Name of the meeting", example = "Mobile App Development")
    @field:NotBlank
    val meetingName: String
)
