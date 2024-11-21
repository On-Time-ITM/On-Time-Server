package org.itm.ontime.global.error

import org.springframework.http.HttpStatus

enum class CommonErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
): ErrorCode {
    // Common Errors
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid input value"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C002", "Invalid type value"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Resource not found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C004", "Method not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C005", "Server error"),

    // Team Errors
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "T001", "Team not found"),
    NOT_TEAM_MEMBER(HttpStatus.FORBIDDEN, "T002", "Not a member of this team"),
    TEAM_MEMBER_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "T003", "Team member limit exceeded"),


    // TODO : Payment Errors

}