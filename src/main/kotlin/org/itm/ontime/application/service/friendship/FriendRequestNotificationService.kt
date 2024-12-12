package org.itm.ontime.application.service.friendship

import org.itm.ontime.domain.user.User
import org.itm.ontime.infrastructure.external.fcm.FCMClient
import org.itm.ontime.infrastructure.external.fcm.FCMTokenRepository
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMServerErrorException
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMTokenUnregisteredException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FriendRequestNotificationService(
    private val fcmClient: FCMClient,
    private val fcmTokenRepository: FCMTokenRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendFriendRequestNotification(receiver: User, requester: User) {
        val fcmToken = fcmTokenRepository.findByUserId(receiver.id)
            ?: return

        try {
            val response = fcmClient.sendNotification(
                token = fcmToken.token,
                title = "새로운 친구 요청",
                body = "${requester.name}님이 친구 요청을 보냈습니다."
            )

            if (response.success == "fail") {
                logger.warn("Failed to send friend request notification to user ${receiver.id}")
            }
        } catch (e: FCMTokenUnregisteredException) {
            logger.warn("FCM token unregistered for user ${receiver.id}", e)
            fcmTokenRepository.delete(fcmToken)
        } catch (e: FCMServerErrorException) {
            logger.error("FCM server error while sending friend request notification", e)
        }
    }
}
