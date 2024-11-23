package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.user.exception.UserErrorCode
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when location tracking fails")
class UserLocationNotFoundException(
    @Schema(description = "User Id that used to find location", example = "123e4567-e89b-12d3-a456-426614174000")
    private val userId: UUID,
    @Schema(description = "Meeting Id that user is belonging", example = "142f42367-d34c-22f2-b365-498740174000")
    private val meetingId: UUID
) : BusinessException(
    UserErrorCode.USER_LOCATION_NOT_FOUND,
    "Failed to track location of user $userId in meeting $meetingId"
)