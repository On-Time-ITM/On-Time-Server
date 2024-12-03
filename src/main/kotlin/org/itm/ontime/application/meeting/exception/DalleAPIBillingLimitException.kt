package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exception thrown when dall-e API billing limit is reached")
class DalleAPIBillingLimitException : BusinessException(
    MeetingErrorCode.DALLE_API_BILLING_LIMIT,
    "Dall-e API billing limit reached"
)

