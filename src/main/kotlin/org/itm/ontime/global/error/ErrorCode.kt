package org.itm.ontime.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
){
    // Common Errors
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid input value"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C002", "Invalid type value"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Resource not found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C004", "Method not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C005", "Server error"),

    // Auth Errors
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "A001", "Unauthorized"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "Invalid token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "Expired token"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A004", "Invalid refresh token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "A005", "User not found"),
    DUPLICATE_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "A006", "Phone number already exists"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "A007", "Invalid password"),

    // Friendship Errors
    FRIENDSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "Friendship not found"),
    DUPLICATE_FRIEND_REQUEST(HttpStatus.CONFLICT, "F002", "Duplicate friend request"),
    SELF_FRIEND_REQUEST(HttpStatus.BAD_REQUEST, "F003", "Cannot send friend request to yourself"),
    UNAUTHORIZED_FRIENDSHIP_ACTION(HttpStatus.FORBIDDEN, "F004", "Unauthorized friendship action"),
    ALREADY_FRIENDS(HttpStatus.CONFLICT, "F005", "Users are already friends"),
    BLOCKED_USER(HttpStatus.FORBIDDEN, "F006", "User is blocked"),

    // Meeting Errors
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "Meeting not found"),
    INVALID_MEETING_TIME(HttpStatus.BAD_REQUEST, "M002", "Invalid meeting time"),
    NOT_MEETING_MEMBER(HttpStatus.FORBIDDEN, "M003", "Not a member of this meeting"),
    ALREADY_CHECKED_ATTENDANCE(HttpStatus.BAD_REQUEST, "M004", "Already checked attendance"),
    INVALID_QR_CODE(HttpStatus.BAD_REQUEST, "M005", "Invalid QR code"),
    LOCATION_TRACKING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "M006", "Failed to track location"),

    // Team Errors
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "T001", "Team not found"),
    NOT_TEAM_MEMBER(HttpStatus.FORBIDDEN, "T002", "Not a member of this team"),
    TEAM_MEMBER_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "T003", "Team member limit exceeded"),

    // TODO : Payment Errors

}