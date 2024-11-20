package org.itm.ontime.domain.friendship.repository

import org.itm.ontime.domain.friendship.entity.Friendship
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FriendshipRepository : JpaRepository<Friendship, UUID>{
}