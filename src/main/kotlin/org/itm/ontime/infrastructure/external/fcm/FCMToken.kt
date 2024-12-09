package org.itm.ontime.infrastructure.external.fcm

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.itm.ontime.domain.common.BaseEntity
import java.util.*

@Entity
@Table(
    name = "fcm_token",
    uniqueConstraints = [
        UniqueConstraint(
            name = "idx_fcm_tokens_user_id",
            columnNames = ["user_id"]
        )
    ]
)
class FCMToken(
    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @Column(nullable = false)
    var token: String,
    ) : BaseEntity()