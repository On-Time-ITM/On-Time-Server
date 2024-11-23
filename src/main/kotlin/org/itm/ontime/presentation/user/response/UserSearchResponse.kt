package org.itm.ontime.presentation.user.response

import java.util.*

data class UserSearchResponse(
    val id: UUID,
    val name: String,
    val phoneNumber: String
) {
    companion object {
        fun of(
            id: UUID,
            name: String,
            phoneNumber: String
        ): UserSearchResponse {
            return UserSearchResponse(id, name, phoneNumber)
        }
    }
}
