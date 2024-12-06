package org.itm.ontime.application.exception.participant.arrival

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when meeting is not found")
class ArrivalNotFoundException(
    @Schema(description = "Meeting ID that arrival was not found", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID,
    @Schema(description = "Participant ID that arrival was not found", example = "550e8400-e29b-41d4-a716-446655440000")
    private val participantId: UUID
) : BusinessException(
    ArrivalErrorCode.ALREADY_NOT_FOUND,
    "Arrival for participant $participantId of meeting $meetingId not found."
)
