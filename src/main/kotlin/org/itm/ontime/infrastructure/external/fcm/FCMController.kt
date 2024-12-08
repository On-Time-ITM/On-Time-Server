package org.itm.ontime.infrastructure.external.fcm

import jakarta.validation.Valid
import org.itm.ontime.infrastructure.external.fcm.dto.MessageRequest
import org.itm.ontime.infrastructure.external.fcm.dto.MessageResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/messages")
class FCMController(
    private var fcmClient: FCMClient
) {
    @PostMapping("/send")
    fun pushNotification(@RequestBody @Valid request: MessageRequest) : ResponseEntity<MessageResponse> {
        val response = fcmClient.sendNotification(request)
        return ResponseEntity.ok(response)
    }
}