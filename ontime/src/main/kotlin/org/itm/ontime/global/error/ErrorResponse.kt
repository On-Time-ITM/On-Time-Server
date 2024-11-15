import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Error Response")
data class ErrorResponse(
    @Schema(description = "Error Code", example = "C001")
    val code: String,

    @Schema(description = "Error Message", example = "Invalid input value")
    val message: String,

    @Schema(description = "HTTP Status Code", example = "400")
    val status: Int,

    @Schema(description = "Field Error List")
    val errors: List<FieldError>? = null
) {
    @Schema(description = "Field Error Information")
    data class FieldError(
        @Schema(description = "Field where the error occurred", example = "email")
        val field: String,

        @Schema(description = "Value that caused the error", example = "invalid-email")
        val value: Any?,

        @Schema(description = "Reason for the error", example = "Invalid email format")
        val reason: String
    )

    companion object {
        fun of(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = errorCode.message,
                status = errorCode.status
            )
        }

        fun of(errorCode: ErrorCode, message: String): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = message,
                status = errorCode.status
            )
        }

        fun of(errorCode: ErrorCode, errors: List<FieldError>): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = errorCode.message,
                status = errorCode.status,
                errors = errors
            )
        }
    }
}