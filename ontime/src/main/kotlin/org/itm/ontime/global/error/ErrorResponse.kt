package org.itm.ontime.global.error

data class ErrorResponse (
    val success: Boolean = false,
    val error: ErrorDetail
){
    data class ErrorDetail(
        val code: String,
        val message: String,
        val errors: List<FieldError>?= null
    )

    data class FieldError(
        val field: String,
        val value: Any?,
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