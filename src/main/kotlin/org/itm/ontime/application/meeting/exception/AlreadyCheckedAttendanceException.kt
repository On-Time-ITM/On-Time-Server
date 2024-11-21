package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.*

@Schema(description = "Exception thrown when attendance is already checked")
class AlreadyCheckedAttendanceException(
    @Schema(description = "User ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val userId: UUID,
    @Schema(description = "Meeting ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    MeetingErrorCode.ALREADY_CHECKED_ATTENDANCE,
    "User $userId has already checked attendance for meeting $meetingId"
)
