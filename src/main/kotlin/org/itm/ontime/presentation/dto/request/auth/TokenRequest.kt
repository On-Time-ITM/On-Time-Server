package org.itm.ontime.presentation.dto.request.auth

data class TokenRequest(
    val accessToken: String,
    val refreshToken: String
)
