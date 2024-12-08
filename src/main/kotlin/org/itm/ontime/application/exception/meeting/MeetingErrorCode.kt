package org.itm.ontime.application.exception.meeting

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class MeetingErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {

    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "Meeting not found"),
    INVALID_MEETING_TIME(HttpStatus.BAD_REQUEST, "M002", "Invalid meeting time"),
    NON_FRIEND_INVITE(HttpStatus.FORBIDDEN, "M003", "Non-friend invites are not allowed"),
    NOT_MEETING_HOST(HttpStatus.FORBIDDEN, "M004", "Not a host of this meeting"),
    NOT_MEETING_PARTICIPANT(HttpStatus.FORBIDDEN, "M005", "Not a participant of this meeting"),

}