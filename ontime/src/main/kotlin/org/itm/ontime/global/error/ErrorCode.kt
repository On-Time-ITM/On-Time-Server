package org.itm.ontime.global.error

enum class ErrorCode (
    val status: Int,
    val code: String,
    val message: String
){
    // Common Errors
    INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
    INVALID_TYPE_VALUE(400, "C002", "Invalid type value"),
    RESOURCE_NOT_FOUND(404, "C003", "Resource not found"),
    METHOD_NOT_ALLOWED(405, "C004", "Method not allowed"),
    INTERNAL_SERVER_ERROR(500, "C005", "Server error"),

    // Auth Errors
    UNAUTHORIZED(401, "A001", "Unauthorized"),
    INVALID_TOKEN(401, "A002", "Invalid token"),
    EXPIRED_TOKEN(401, "A003", "Expired token"),
    INVALID_REFRESH_TOKEN(401, "A004", "Invalid refresh token"),
    USER_NOT_FOUND(404, "A005", "User not found"),
    DUPLICATE_PHONE_NUMBER(400, "A006", "Phone number already exists"),
    INVALID_PASSWORD(400, "A007", "Invalid password"),

    // Meeting Errors
    MEETING_NOT_FOUND(404, "M001", "Meeting not found"),
    INVALID_MEETING_TIME(400, "M002", "Invalid meeting time"),
    NOT_MEETING_MEMBER(403, "M003", "Not a member of this meeting"),
    ALREADY_CHECKED_ATTENDANCE(400, "M004", "Already checked attendance"),
    INVALID_QR_CODE(400, "M005", "Invalid QR code"),
    LOCATION_TRACKING_ERROR(500, "M006", "Failed to track location"),

    // Team Errors
    TEAM_NOT_FOUND(404, "T001", "Team not found"),
    NOT_TEAM_MEMBER(403, "T002", "Not a member of this team"),
    TEAM_MEMBER_LIMIT_EXCEEDED(400, "T003", "Team member limit exceeded"),

    // TODO : Payment Errors

}