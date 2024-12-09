package org.itm.ontime.infrastructure.external.fcm

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.infrastructure.external.fcm.dto.FCMTokenRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "FCM", description = "Firebase Cloud Messaging (FCM) APIs")
class FCMController(
    private var fcmClient: FCMClient
) {
    @Operation(
        description = "Save token for Firebase Cloud Messaging (FCM)",
        summary = "Save token for FCM",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Token saved successfully"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid token format"
            )
        ]
    )
    @PostMapping("/token")
    fun saveToken(@RequestBody @Valid request: FCMTokenRequest) : ResponseEntity<Unit> {
        fcmClient.saveToken(request)
        return ResponseEntity.ok().build()
    }

//    @PostMapping("/send")
//    fun pushNotification(@RequestBody @Valid request: MessageRequest) : ResponseEntity<MessageResponse> {
//        val response = fcmClient.sendNotification(request)
//        return ResponseEntity.ok(response)
//    }
}