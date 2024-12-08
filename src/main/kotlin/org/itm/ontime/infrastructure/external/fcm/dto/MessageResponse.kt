package org.itm.ontime.infrastructure.external.fcm.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable


@Schema(description = "Result of sending messages")
data class MessageResponse(
    @Schema(description = "Is a message sent to user", example = "fail")
    val success : String = "success",
    @Schema(description = "Token of the user", example = "some_token")
    @Nullable
    val token: String?
) {
    companion object {
        @JvmStatic
        fun of(success: String, token: String?) : MessageResponse {
            return MessageResponse(success, token)
        }

        @JvmStatic
        fun of(success: String) : MessageResponse {
            return MessageResponse(success, null)
        }
    }
}
