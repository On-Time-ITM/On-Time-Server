package org.itm.ontime.infrastructure.repository.friendship

import org.itm.ontime.domain.friendship.Friendship
import org.itm.ontime.domain.friendship.FriendshipStatus
import org.itm.ontime.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FriendshipRepository : JpaRepository<Friendship, UUID>{
    fun findByRequesterAndReceiver(requester: User, receiver: User): Friendship?
    fun findByReceiverAndStatus(receiver: User, status: FriendshipStatus): List<Friendship>
    fun existsByRequesterAndReceiverAndStatus(requester: User, receiver: User, status: FriendshipStatus): Boolean

    @Query("""
        SELECT f FROM Friendship f 
        WHERE f.status = 'ACCEPTED' 
        AND (f.requester = :user OR f.receiver = :user)
    """)
    fun findAllAcceptedFriendships(user: User): List<Friendship>

    @Query("""
        SELECT f FROM Friendship f 
        WHERE (f.requester.id = :userId AND f.receiver.id = :friendId)
        OR (f.requester.id = :friendId AND f.receiver.id = :userId)
    """)
    fun findByUsers(userId: UUID, friendId: UUID): Friendship?

    fun existsByStatusAndRequesterIdAndReceiverId(
        status: FriendshipStatus,
        requesterId: UUID,
        receiverId: UUID
    ): Boolean
}