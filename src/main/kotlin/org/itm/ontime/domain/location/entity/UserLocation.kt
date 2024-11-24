package org.itm.ontime.domain.location.entity

import jakarta.persistence.*
import org.itm.ontime.domain.meeting.entity.Meeting
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "user_location")
class UserLocation private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meeting_id", nullable = false) var meeting: Meeting,

    @Embedded
    var location: Location
) : BaseEntity() {
    fun updateLocation(location: Location) {
        this.location = location
    }

    companion object {
        fun create(user: User, meeting: Meeting, location: Location): UserLocation {
            val userLocation = UserLocation(user, meeting, location)
            meeting.addUserLocation(userLocation)
            return userLocation
        }
    }
}
