package org.itm.ontime.presentation.dto.request.auth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class TokenRefreshRequest(
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1...")
    @field:NotBlank(message = "Refresh token is required")
    val refreshToken: String
)
