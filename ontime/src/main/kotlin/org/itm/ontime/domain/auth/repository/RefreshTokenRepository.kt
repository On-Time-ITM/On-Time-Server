package org.itm.ontime.domain.auth.repository

import org.itm.ontime.domain.auth.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByUserId(userId: UUID)
}