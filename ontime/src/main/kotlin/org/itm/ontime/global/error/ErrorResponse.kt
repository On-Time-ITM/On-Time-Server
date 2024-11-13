package org.itm.ontime.global.error

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "Error Response")
data class ErrorResponse(
    @Schema(description = "Success status of the request", example = "false")
    val success: Boolean = false,

    @Schema(description = "Detailed error information")
    val error: ErrorDetail
) {
    @Schema(description = "Detailed error information")
    data class ErrorDetail(
        @Schema(description = "Error code identifying the error type", example = "USER_NOT_FOUND")
        val code: String,

        @Schema(description = "Human readable error message", example = "User not found")
        val message: String,

        @Schema(description = "List of field validation errors", nullable = true)
        val errors: List<FieldError>? = null
    )

    @Schema(description = "Field validation error details")
    data class FieldError(
        @Schema(description = "Name of the field that caused the error", example = "username")
        val field: String,

        @Schema(description = "The invalid value that was provided", example = "")
        val value: Any?,

        @Schema(description = "Reason why the field value is invalid", example = "This field is required")
        val reason: String
    )

    companion object {
        fun of(errorCode: ErrorCode) = ErrorResponse(
            error = ErrorDetail(
                code = errorCode.code,
                message = errorCode.message
            )
        )

        fun of(errorCode: ErrorCode, errors: List<FieldError>) = ErrorResponse(
            error = ErrorDetail(
                code = errorCode.code,
                message = errorCode.message,
                errors = errors
            )
        )
    }
}