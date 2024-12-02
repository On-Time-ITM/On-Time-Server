package org.itm.ontime.presentation.user.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@Schema(description = "Request to search for a user")
data class UserSearchRequest(
    @Schema(description = "User phone number", example = "010-1234-5678")
    @field:NotBlank(message = "Phone number is required")
    @field:Pattern(
        regexp = "^010-\\\\d{4}-\\\\d{4}\$",
        message = "Phone number must be in format: 010-XXXX-XXXX"
    )
    val phoneNumber: String
)
