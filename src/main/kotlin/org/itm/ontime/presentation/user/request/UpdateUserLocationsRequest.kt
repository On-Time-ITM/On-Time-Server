package org.itm.ontime.presentation.user.request

import org.itm.ontime.domain.common.Location
import java.util.*

data class UpdateUserLocationsRequest (
    val meetingId: UUID,
    val locations: List<HashMap<UUID, Location>>
)