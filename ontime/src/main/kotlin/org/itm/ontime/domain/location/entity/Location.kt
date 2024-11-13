package org.itm.ontime.domain.location.entity

import jakarta.persistence.*
import org.itm.ontime.domain.PrimaryKeyEntity
import org.itm.ontime.domain.user.entity.User
import java.time.LocalDateTime

@Entity
class Location(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,

    @Column(nullable = false)
    private val latitude: Double,

    @Column(nullable = false)
    private val longitude: Double
) : PrimaryKeyEntity() {

    @Column(nullable = false)
    private val updatedAt: LocalDateTime = LocalDateTime.now()

}