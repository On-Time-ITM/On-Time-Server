package org.itm.ontime.domain.user.entity

import jakarta.persistence.*
import lombok.Getter
import org.itm.ontime.global.entity.BaseEntity
import org.itm.ontime.domain.common.Location

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