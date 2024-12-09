package org.itm.ontime.infrastructure.external.fcm


import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.*
import org.itm.ontime.application.exception.user.UserNotFoundException
import org.itm.ontime.infrastructure.external.fcm.dto.FCMTokenRequest
import org.itm.ontime.infrastructure.external.fcm.dto.MessageResponse
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMServerErrorException
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMTokenUnregisteredException
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.springframework.stereotype.Service


@Service
class FCMClient(
    private var firebaseApp: FirebaseApp,
    private val fcmTokenRepository: FCMTokenRepository,
    private val userRepository: UserRepository
) {
    fun saveToken(request: FCMTokenRequest) {
        val user = userRepository.findById(request.userId)
            .orElseThrow{ UserNotFoundException.fromId(request.userId) }

        val token = FCMToken(user.id, request.token)
        fcmTokenRepository.save(token)
    }

    fun sendNotification(token: String, title: String, body: String) : MessageResponse {
        try {
            val message = Message.builder()
                .setToken(token)
                .setNotification(
                    Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build()
                )
                .build()

            FirebaseMessaging.getInstance(firebaseApp)
                .send(message)

        } catch (e: FirebaseMessagingException) {
            when(e.messagingErrorCode) {
                MessagingErrorCode.INVALID_ARGUMENT ->
                    return MessageResponse.of("fail", token)

                MessagingErrorCode.UNREGISTERED ->
                    throw FCMTokenUnregisteredException(token)

                else -> throw  FCMServerErrorException()
            }
        } catch (e: Exception) {
            throw FCMServerErrorException()
        }
        return MessageResponse.of("success")
    }
}