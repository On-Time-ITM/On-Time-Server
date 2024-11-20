package org.itm.ontime.application.friendship.service

import jakarta.persistence.Id
import org.itm.ontime.application.friendship.exception.*
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.friendship.entity.FriendshipStatus
import org.itm.ontime.domain.friendship.repository.FriendshipRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.global.error.ErrorCode
import org.itm.ontime.presentation.friendship.request.FriendshipAcceptRequest
import org.itm.ontime.presentation.friendship.request.FriendshipRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FriendshipService (
    private val userRepository: UserRepository,
    private val friendshipRepository: FriendshipRepository
){
    fun sendFriendRequest(request: FriendshipRequest) : UUID {
        val requester = userRepository.findById(request.requesterId)
            .orElseThrow{ UserNotFoundException.fromId(request.requesterId) }

        val receiver = userRepository.findById(request.receiverId)
            .orElseThrow{ UserNotFoundException.fromId(request.receiverId) }

        if (request.requesterId == request.receiverId) {
            throw SelfFriendRequestException(request.requesterId)
        }

        if (friendshipRepository.findByRequesterAndReceiver(requester, receiver) != null) {
            throw DuplicateFriendRequestException(request.requesterId, request.receiverId)
        }

        val friendship = Friendship(
            requester = requester,
            receiver = receiver,
            FriendshipStatus.PENDING
        )

        friendshipRepository.save(friendship)

        return friendship.id
    }

    fun acceptFriendRequest(request: FriendshipAcceptRequest) : UUID {
        val friendship = validateFriendship(request)

        friendship.status = FriendshipStatus.ACCEPTED
        friendshipRepository.save(friendship)

        return friendship.id
    }

    fun rejectFriendRequest(request: FriendshipAcceptRequest) : UUID {
        val friendship = validateFriendship(request)

        friendship.status = FriendshipStatus.REJECTED
        friendshipRepository.save(friendship)

        return friendship.id
    }

    private fun validateFriendship(request: FriendshipAcceptRequest): Friendship {
        val friendship = friendshipRepository.findById(request.friendshipId)
            .orElseThrow { FriendshipNotFoundException(request.friendshipId) }

        if (friendship.id != request.receiverId) {
            throw UnauthorizedFriendshipException(request.receiverId, friendship.id)
        }

        if (friendship.status != FriendshipStatus.PENDING) {
            throw InvalidFriendshipStatusException(friendship.id, request.receiverId)
        }
        return friendship
    }

//    fun getFriendList(userId: UUID) : List<Friendship> {
//        val user = userRepository.findById(userId)
//            .orElseThrow{ UserNotFoundException.fromId(userId) }
//
//        return friendshipRepository.findByReceiverAndStatus(user, FriendshipStatus.ACCEPTED)
//            .map { friendship ->
//                if (friendship.requester.id == userId) friendship.receiver else friendship.requester
//                // TODO : 로직 재검토
//            }
//    }
//
//    fun  getReceivedFriendRequests(userId: UUID): List


}