package org.itm.ontime.infrastructure.external.openAI

import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class OpenAIErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
): ErrorCode {
    OPENAI_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "O001", "An error occurred while using OpenAI API"),
    OPENAI_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "O002", "Exceeded the OpenAI API request limit"),
    OPENAI_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "O003", "Invalid OpenAI API request"),
    OPENAI_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "O004", "Internal error occurred while using OpenAI API"),
    OPENAI_INVALID_RESPONSE(HttpStatus.INTERNAL_SERVER_ERROR, "O005", "Invalid OpenAI API response"),
}