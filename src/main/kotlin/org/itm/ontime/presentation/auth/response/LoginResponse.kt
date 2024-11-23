package org.itm.ontime.presentation.auth.response

import java.util.*

data class LoginResponse(
    val tokenResponse: TokenResponse,
    val userId: UUID,
    val name: String,
    val phoneNumber: String,
    val tardinessRate: Int
) {
    companion object {
        fun of(
            tokenResponse: TokenResponse,
            userId: UUID,
            name: String,
            phoneNumber: String,
            tardinessRate: Int
        ): LoginResponse {
            return LoginResponse(tokenResponse, userId, name, phoneNumber, tardinessRate)
        }
    }
}
