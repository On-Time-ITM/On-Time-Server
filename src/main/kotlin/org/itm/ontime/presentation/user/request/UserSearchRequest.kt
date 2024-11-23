package org.itm.ontime.presentation.user.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Request to search for a user")
data class UserSearchRequest(
    @Schema(
        description = "Phone number",
        example = "010-1234-5678"
    )
    @field:NotBlank
    val phoneNumber: String
)
