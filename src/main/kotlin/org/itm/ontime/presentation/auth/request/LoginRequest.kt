package org.itm.ontime.presentation.auth.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class LoginRequest(
    @Schema(description = "Phone Number", example = "010-1234-5678")
    @field:Pattern(
        regexp = "^010-\\d{4}-\\d{4}$",
        message = "Phone number must be in format: 010-XXXX-XXXX"
    )
    val phoneNumber: String,

    @Schema(description = "Password", example = "pwd123@")
    @field:NotBlank(message = "Password is required")
    val password: String
)
