package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when user is not a meeting participant")
class NotMeetingParticipantException(
    @Schema(description = "Participant ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val participantId: UUID,
    @Schema(description = "Meeting ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    MeetingErrorCode.NOT_MEETING_HOST,
    "User $participantId is not a host of meeting $meetingId"
)