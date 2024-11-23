package org.itm.ontime.presentation.user.request

import org.itm.ontime.domain.common.Location
import java.util.*

data class UserLocationResponse (
    val userLocationId: UUID,
    val userId: UUID,
    val meetingId: UUID,
    val location: Location
) {
    companion object {
        fun of(userLocationId: UUID, userId: UUID, meetingId: UUID, location: Location): UserLocationResponse {
            return UserLocationResponse(userLocationId, userId, meetingId, location)
        }
    }
}