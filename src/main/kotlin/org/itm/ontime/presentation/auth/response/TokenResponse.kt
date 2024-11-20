package org.itm.ontime.presentation.auth.response

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class TokenResponse(
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1...")
    val accessToken: String,

    @Schema(description = "JWT refresh token", example = "eyJhbGciOiJIUzI1...")
    val refreshToken: String,

    @Schema(description = "Access token expiration time in seconds", example = "3600")
    val expiresIn: Long,

    @Schema(description = "The unique identifier of the user", example = "123e4567-e89b-12d3-a456-426655440000")
    val userId: UUID
)
