package org.itm.ontime.application.exception.participant.participant

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import org.itm.ontime.application.exception.meeting.MeetingErrorCode
import java.util.*

@Schema(description = "Exception thrown when user is not a meeting participant")
class NotParticipantException(
    @Schema(description = "Participant ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val participantId: UUID,
    @Schema(description = "Meeting ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    MeetingErrorCode.NOT_MEETING_HOST,
    "User $participantId is not a host of meeting $meetingId"
)