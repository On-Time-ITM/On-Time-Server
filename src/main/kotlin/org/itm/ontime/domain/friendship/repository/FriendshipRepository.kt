package org.itm.ontime.domain.friendship.repository

import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.friendship.entity.FriendshipStatus
import org.itm.ontime.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface FriendshipRepository : JpaRepository<Friendship, UUID>{
    fun findByRequesterAndReceiver(requester: User, receiver: User): Friendship?
    fun findByReceiverAndStatus(receiver: User, status: FriendshipStatus): List<Friendship>

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