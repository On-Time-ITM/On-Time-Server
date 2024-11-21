package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.UUID

@Schema(description = "Exception thrown when user is not a meeting host")
class NotMeetingHostException(
    @Schema(description = "Host ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val hostId: UUID,
    @Schema(description = "Meeting ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    MeetingErrorCode.NOT_MEETING_HOST,
    "User $hostId is not a host of meeting $meetingId"
)