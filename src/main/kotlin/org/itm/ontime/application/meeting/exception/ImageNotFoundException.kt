package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when profile image is not found")
class ImageNotFoundException(
    @Schema(description = "Profile image ID for which the QR code was not found", example = "550e8400-e29b-41d4-a716-446655440000")
    private val profileImageId: UUID
) : BusinessException(
    MeetingErrorCode.QR_CODE_NOT_FOUND,
    "The QR code for meeting $profileImageId was not found"
)

