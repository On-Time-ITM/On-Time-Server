package org.itm.ontime.presentation.friendship.request

import java.util.UUID

// TODO : Description
data class FriendshipAcceptRequest(
    val friendshipId: UUID,
    val receiverId: UUID
)
