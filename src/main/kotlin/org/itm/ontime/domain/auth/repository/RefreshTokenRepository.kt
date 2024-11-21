package org.itm.ontime.domain.auth.repository

import org.itm.ontime.domain.auth.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken
    fun deleteByUserId(userId: UUID): RefreshToken
    fun findByUserId(userId: UUID): Optional<RefreshToken>
}