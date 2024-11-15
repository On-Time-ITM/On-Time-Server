package org.itm.ontime.domain.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "refresh_tokens")
class RefreshToken(
    @Id
    val userId: UUID,

    @Column(nullable = false)
    var token: String,

    @Column(nullable = false)
    var expiresAt: LocalDateTime = LocalDateTime.now().plusDays(14)
) {
    fun isExpired(): Boolean = LocalDateTime.now().isAfter(expiresAt)
}