package org.itm.ontime.presentation.dto.response.auth

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.presentation.dto.response.auth.internal.TokenInfo
import org.itm.ontime.presentation.dto.response.user.UserResponse

@Schema(description = "Response for authentication")
data class AuthResponse(
    @Schema(description = "User information")
    val userInfo: UserResponse,
    @Schema(description = "Token information")
    val tokenInfo: TokenInfo
) {
    companion object {
        @JvmStatic
        fun of(userInfo: UserResponse, tokenInfo: TokenInfo) =
            AuthResponse(userInfo, tokenInfo)
    }

}
