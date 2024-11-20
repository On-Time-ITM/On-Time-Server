package org.itm.ontime.domain.auth.entity

import jakarta.persistence.*
import lombok.Getter
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.common.BaseEntity
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Getter
@Table(
    name = "refresh_tokens",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_refresh_tokens_user_id",
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
    var expiresAt: LocalDateTime = LocalDateTime.now().plusDays(14),

) : BaseEntity() {
    fun isExpired(): Boolean = LocalDateTime.now().isAfter(expiresAt)
}