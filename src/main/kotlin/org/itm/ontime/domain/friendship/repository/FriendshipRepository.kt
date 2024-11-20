package org.itm.ontime.domain.friendship.repository

import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.friendship.entity.FriendshipStatus
import org.itm.ontime.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface FriendshipRepository : JpaRepository<Friendship, UUID>{
    fun findByRequesterAndReceiver(requester: User, receiver: User): Friendship?
    @Query("""
        SELECT f FROM Friendship f 
        WHERE f.status = 'ACCEPTED' 
        AND (f.requester.id = :userId OR f.receiver.id = :userId)
    """)
    fun findAllAcceptedFriendships(userId: UUID): List<Friendship>
    @Query("""
        SELECT f FROM Friendship f 
        WHERE (f.requester.id = :userId1 AND f.receiver.id = :userId2)
        OR (f.requester.id = :userId2 AND f.receiver.id = :userId1)
    """)
    fun findByUsers(userId: UUID, friendId: UUID): Friendship?
}