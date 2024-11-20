package org.itm.ontime.presentation.friendship.request

import java.util.UUID

// TODO : Description
data class FriendshipDeleteRequest(
    val userId: UUID,
    val friendId: UUID
) {
}
