package org.itm.ontime.domain.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.itm.ontime.domain.common.BaseEntity
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "refresh_token",
    uniqueConstraints = [
        UniqueConstraint(
            name = "idx_refresh_tokens_user_id",
            columnNames = ["user_id"]
        )
    ]
)
class RefreshToken(
    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @Column(nullable = false)
    var token: String,

    @Column(nullable = false)
    var expiresAt: Date = Date.from(LocalDateTime.now().plusDays(30).toInstant(null)),

    ) : BaseEntity()