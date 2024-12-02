package org.itm.ontime.presentation.user.request

data class TokenRequest(
    val accessToken: String,
    val refreshToken: String
)
