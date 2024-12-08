package org.itm.ontime.infrastructure.external.fcm


import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.*
import org.itm.ontime.infrastructure.external.fcm.dto.MessageRequest
import org.itm.ontime.infrastructure.external.fcm.dto.MessageResponse
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMServerErrorException
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMTokenNotFoundException
import org.springframework.stereotype.Service


@Service
class FCMClient(
    private var firebaseApp: FirebaseApp
) {
    fun sendNotification(request: MessageRequest) : MessageResponse {
        try {
            val message = Message.builder()
                .setToken(request.token)
                .setNotification(
                    Notification.builder()
                        .setTitle(request.title)
                        .setBody(request.body)
                        .build()
                )
                .build()

            FirebaseMessaging.getInstance(firebaseApp)
                .send(message)

        } catch (e: FirebaseMessagingException) {
            when(e.messagingErrorCode) {
                MessagingErrorCode.INVALID_ARGUMENT ->
                    return MessageResponse.of("fail", request.token)

                MessagingErrorCode.UNREGISTERED ->
                    throw FCMTokenNotFoundException(request.token)

                else -> throw  FCMServerErrorException()
            }
        } catch (e: Exception) {
            throw FCMServerErrorException()
        }
        return MessageResponse.of("success")
    }
}