package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when location tracking fails")
class LocationTrackingErrorException(
    @Schema(description = "Error message", example = "GPS signal not found")
    private val userId: UUID
) : BusinessException(
    MeetingErrorCode.LOCATION_TRACKING_ERROR,
    "Failed to track location of user $userId"
)