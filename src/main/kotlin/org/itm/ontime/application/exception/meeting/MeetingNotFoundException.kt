package org.itm.ontime.application.exception.meeting

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when meeting is not found")
class MeetingNotFoundException(
    @Schema(description = "Meeting ID that was not found", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    MeetingErrorCode.MEETING_NOT_FOUND,
    "Meeting not found: $meetingId"
)