package org.itm.ontime.infrastructure.external.fcm.dto

import java.util.*

data class FCMTokenRequest(
    val userId: UUID,
    val token: String,
)
