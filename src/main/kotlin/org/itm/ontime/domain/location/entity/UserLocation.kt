package org.itm.ontime.domain.location.entity

import jakarta.persistence.*
import lombok.Getter
import org.itm.ontime.domain.meeting.entity.Meeting
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Getter
@Table(name = "user_locations")
class UserLocation(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) val user: User,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="meeting_id", nullable = false) val meeting: Meeting,

    @Embedded
    var location: Location
) : BaseEntity() {
    fun updateLocation(location: Location) {
        this.location = location
    }
}
