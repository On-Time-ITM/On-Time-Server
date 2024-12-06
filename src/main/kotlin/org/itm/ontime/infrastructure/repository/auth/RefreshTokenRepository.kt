package org.itm.ontime.infrastructure.repository.auth

import org.itm.ontime.domain.auth.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByUserId(userId: UUID)
    fun findByUserId(userId: UUID): RefreshToken?
}