package org.itm.ontime.presentation.user.request

import org.itm.ontime.domain.common.Location
import java.util.*

data class UserLocationRequest (
    val userId: UUID,
    val meetingId: UUID,
    val location: Location
)