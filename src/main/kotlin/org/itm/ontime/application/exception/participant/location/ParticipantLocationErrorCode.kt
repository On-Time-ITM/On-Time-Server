package org.itm.ontime.application.exception.participant.location

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class ParticipantLocationErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {
    PARTICIPANT_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "L001", "Participant location not found"),
}