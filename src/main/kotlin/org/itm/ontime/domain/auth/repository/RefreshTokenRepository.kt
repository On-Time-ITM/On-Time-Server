package org.itm.ontime.domain.auth.repository

import org.itm.ontime.domain.auth.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByUserId(userId: UUID)
    fun findByUserId(userId: UUID): RefreshToken?
}