package org.itm.ontime.presentation.user.request

import org.itm.ontime.domain.common.Location

data class UserLocationRequest (
    val userId: String,
    val meetingId: String,
    val location: Location
)