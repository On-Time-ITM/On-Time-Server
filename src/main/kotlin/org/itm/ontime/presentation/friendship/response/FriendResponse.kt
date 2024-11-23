package org.itm.ontime.presentation.friendship.response

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "Response DTO for friend information")
data class FriendResponse(
    @Schema(description = "Friend's unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    val id: UUID,

    @Schema(description = "Friend's phone number", example = "010-1234-5678")
    val phoneNumber: String,

    @Schema(description = "Friend's name", example = "Saeyeon Lim")
    val name: String,

    @Schema(description = "Friend's tardiness rate", example = "40")
    val tardinessRate: Int
) {
    companion object {
        @JvmStatic
        fun of(
            id: UUID,
            phoneNumber: String,
            name: String,
            tardinessRate: Int
        ): FriendResponse {
            return FriendResponse(id, phoneNumber, name, tardinessRate)
        }
    }
}

