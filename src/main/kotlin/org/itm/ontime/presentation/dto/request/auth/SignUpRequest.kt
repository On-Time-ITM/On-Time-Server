package org.itm.ontime.presentation.dto.request.auth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @Schema(description = "User phone number", example = "010-1234-5678")
    @field:NotBlank(message = "Phone number is required")
    @field:Pattern(
        regexp = "^010-\\\\d{4}-\\\\d{4}\$",
        message = "Phone number must be in format: 010-XXXX-XXXX"
    )
    val phoneNumber: String,

    @Schema(description = "Password", example = "pwd123@")
    @field:NotBlank(message = "Password is required")
    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
        message = "Password must be 8-20 characters containing letters, numbers and special characters"
    )
    val password: String,

    @Schema(description = "User name", example = "Saeyeon Lim")
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters")
    val name: String
)
