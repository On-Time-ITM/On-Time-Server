package org.itm.ontime.infrastructure.external.fcm.dto

data class MessageRequest(
    val token: String,
    val title: String,
    val body: String,
)
