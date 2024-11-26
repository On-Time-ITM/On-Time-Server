package org.itm.ontime.infrastructure.external.openAI

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when OpenAI internal error occurs")
class OpenAIInternalErrorException(
    @Schema(description = "Meeting ID that was invalid", example = "550e8400-e29b-41d4-a716-446655440000")
    private val meetingId: UUID
): BusinessException(
    OpenAIErrorCode.OPENAI_INTERNAL_ERROR,
    "Invalid request: $meetingId"
)