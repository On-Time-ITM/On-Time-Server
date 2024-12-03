package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exception thrown when dall-e API returns an error")
class DalleAPIErrorException : BusinessException(
    MeetingErrorCode.DALLE_API_ERROR,
    "Dall-e API returned an error"
)

