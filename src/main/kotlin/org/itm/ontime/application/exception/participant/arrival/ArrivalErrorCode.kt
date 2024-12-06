package org.itm.ontime.application.exception.participant.arrival

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class ArrivalErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {

    ALREADY_NOT_FOUND(HttpStatus.NOT_FOUND, "A001", "Arrival not found"),
    ALREADY_ARRIVED(HttpStatus.BAD_REQUEST, "A002", "Already arrived"),

}