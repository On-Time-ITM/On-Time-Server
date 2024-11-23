package org.itm.ontime.domain.user.repository

import org.itm.ontime.domain.user.entity.UserLocation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserLocationRepository : JpaRepository<UserLocation, UUID> {
    fun findByMeetingId(meetingId: UUID): List<UserLocation>
    fun findByUserIdAndMeetingId(userId: UUID, meetingId: UUID): UserLocation?
}