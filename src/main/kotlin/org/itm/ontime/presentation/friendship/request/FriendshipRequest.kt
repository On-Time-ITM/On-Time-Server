package org.itm.ontime.presentation.friendship.request

import java.util.UUID

// TODO : Description
data class FriendshipRequest(
    val requesterId: UUID,
    val receiverId: UUID
) {
}