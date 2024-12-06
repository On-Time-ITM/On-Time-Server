package org.itm.ontime.presentation.dto.response.auth.internal

import org.itm.ontime.domain.auth.RefreshToken

data class TokenInfo(
    val accessToken: String,
    val refreshToken: RefreshToken,
    val accessTokenExpiresIn: Long,
) {
    companion object {
        @JvmStatic
        fun of(
            accessToken: String,
            refreshToken: RefreshToken,
            accessTokenExpiresIn: Long
        ) = TokenInfo(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExpiresIn = accessTokenExpiresIn
        )
    }
}
