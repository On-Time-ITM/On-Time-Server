package org.itm.ontime.presentation.user.response

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "Response for user search")
data class UserSearchResponse(
    @Schema(
        description = "User ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val id: UUID,

    @Schema(
        description = "User name",
        example = "Saeyeon Lim"
    )
    @field:NotBlank
    val name: String,

    @Schema(
        description = "Phone number",
        example = "010-1234-5678"
    )
    @field:NotBlank
    val phoneNumber: String
) {
    companion object {
        @JvmStatic
        fun of(
            id: UUID,
            name: String,
            phoneNumber: String
        ): UserSearchResponse {
            return UserSearchResponse(id, name, phoneNumber)
        }
    }
}
