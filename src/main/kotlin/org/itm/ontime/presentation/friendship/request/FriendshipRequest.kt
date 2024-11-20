package org.itm.ontime.presentation.friendship.request

import java.util.UUID

data class FriendshipRequest(
    val requesterId: UUID,
    val receiverId: UUID
) {
}