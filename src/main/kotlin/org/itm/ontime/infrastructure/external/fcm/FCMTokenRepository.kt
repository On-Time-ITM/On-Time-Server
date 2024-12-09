package org.itm.ontime.infrastructure.external.fcm

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FCMTokenRepository : JpaRepository<FCMToken, UUID>{
    fun findByUserId(userId: UUID) : FCMToken?
}