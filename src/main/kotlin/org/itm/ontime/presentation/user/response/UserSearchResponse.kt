package org.itm.ontime.presentation.user.response

import java.util.UUID

data class UserSearchResponse(
    val id: UUID,
    val name: String,
    val phoneNumber: String
)
