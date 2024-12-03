package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exception thrown when profile image creation failed")
class ImageGenerateFailedException : BusinessException(
    MeetingErrorCode.IMAGE_GENERATION_FAILED,
    "The profile image generation was failed"
)

