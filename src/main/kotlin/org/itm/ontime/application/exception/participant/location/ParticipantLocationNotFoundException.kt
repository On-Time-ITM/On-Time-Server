package org.itm.ontime.application.exception.participant.location

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when location is not found")
class ParticipantLocationNotFoundException(
    @Schema(description = "Participant ID that used to find location", example = "123e4567-e89b-12d3-a456-426614174000")
    private val participantId: UUID,
    @Schema(description = "Meeting Id that participant is belonging", example = "142f42367-d34c-22f2-b365-498740174000")
    private val meetingId: UUID
) : BusinessException(
    ParticipantLocationErrorCode.PARTICIPANT_LOCATION_NOT_FOUND,
    "Failed to find location of user $participantId in meeting $meetingId"
)