package org.itm.ontime.application.exception.participant.arrival

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when participant is already arrived")
class AlreadyArrivedException(
    @Schema(description = "Participant ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val participantId: UUID,
    @Schema(description = "Meeting ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
) : BusinessException(
    ArrivalErrorCode.ALREADY_ARRIVED,
    "Participant $participantId of meeting $meetingId already arrived for meeting."
)
