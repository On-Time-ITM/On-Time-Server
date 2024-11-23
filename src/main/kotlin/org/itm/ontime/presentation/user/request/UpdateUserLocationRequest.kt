package org.itm.ontime.presentation.user.request

import org.itm.ontime.domain.common.Location
import java.util.UUID

data class UpdateUserLocationRequest (
    val userLocationId: UUID,
    val userId: UUID,
    val meetingId: UUID,
    val location: Location
)