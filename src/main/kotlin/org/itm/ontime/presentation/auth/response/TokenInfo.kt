package org.itm.ontime.presentation.auth.response

import org.itm.ontime.domain.auth.entity.RefreshToken

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
