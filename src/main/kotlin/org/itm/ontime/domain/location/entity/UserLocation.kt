package org.itm.ontime.domain.location.entity

import jakarta.persistence.*
import lombok.Getter
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.common.BaseEntity

@Entity
@Getter
@Table(name = "user_locations")
class UserLocation(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,

    @Embedded
    var location: Location
) : BaseEntity() {
}