package org.itm.ontime.application.meeting.exception

import org.itm.ontime.global.error.ErrorCode
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
    ALREADY_CHECKED_ATTENDANCE(HttpStatus.BAD_REQUEST, "M006", "Already checked attendance"),

    QR_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "Q001", "QR code not found"),
    QR_CODE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Q002", "QR code already exists"),

    IMAGE_GENERATION_FAILED(HttpStatus.BAD_REQUEST, "P001", "Profile image generation failed"),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "P002", "Profile image not found"),

    DALLE_API_BILLING_LIMIT(HttpStatus.BAD_REQUEST, "D001", "Dalle API billing limit reached"),
    DALLE_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "D002", "Dalle API error"),
}