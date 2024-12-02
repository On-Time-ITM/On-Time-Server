package org.itm.ontime.application.friendship.service

import org.itm.ontime.application.friendship.exception.*
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.friendship.entity.FriendshipStatus
import org.itm.ontime.domain.friendship.repository.FriendshipRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.friendship.request.AcceptFriendshipRequest
import org.itm.ontime.presentation.friendship.request.DeleteFriendshipRequest
import org.itm.ontime.presentation.friendship.request.SendFriendshipRequest
import org.itm.ontime.presentation.friendship.response.FriendRequestResponse
import org.itm.ontime.presentation.user.response.UserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FriendshipService (
    private val userRepository: UserRepository,
    private val friendshipRepository: FriendshipRepository
){
    @Transactional(readOnly = true)
    fun searchFriendByPhoneNumber(phoneNumber: String) : UserResponse {
        val user = userRepository.findByPhoneNumber(phoneNumber)
            ?: throw UserNotFoundException.fromPhoneNumber(phoneNumber)

        return UserResponse.of(
            id = user.id,
            name = user.name,
            phoneNumber = user.phoneNumber
        )
    }

    fun sendFriendRequest(request: SendFriendshipRequest) : UUID {
        val requester = userRepository.findById(request.requesterId)
            .orElseThrow{ UserNotFoundException.fromId(request.requesterId) }

        val receiver = userRepository.findByPhoneNumber(request.receiverPhoneNumber)
            ?: throw UserNotFoundException.fromPhoneNumber(request.receiverPhoneNumber)

        if (isValidateRequest(requester, receiver)) {
            throw AlreadyFriendsException(requester.id, receiver.id)
        }

        if (isValidateRequest(requester, receiver, FriendshipStatus.PENDING)) {
            throw AlreadyExistsFriendRequestException(requester.id, receiver.id)
        }

        val friendship = Friendship(requester, receiver)
        friendshipRepository.save(friendship)

        requester.sentFriendships.add(friendship)
        receiver.receivedFriendships.add(friendship)

        return friendship.id
    }

    private fun isValidateRequest(
        requester: User, receiver: User,
        status: FriendshipStatus = FriendshipStatus.ACCEPTED
    ) = requester.sentFriendships.any { it.receiver.id == receiver.id && it.status == status } ||
                requester.receivedFriendships.any { it.requester.id == receiver.id && it.status == status }

    @Transactional
    fun acceptFriendRequest(request: AcceptFriendshipRequest) : UUID {
        val friendship = getValidFriendship(request)
        friendship.accept()

        return friendship.id
    }

    @Transactional
    fun rejectFriendRequest(request: AcceptFriendshipRequest) : UUID {
        val friendship = getValidFriendship(request)

        friendshipRepository.delete(friendship)
        return friendship.id
    }

    private fun getValidFriendship(request: AcceptFriendshipRequest) : Friendship {
        val receiver = userRepository.findById(request.receiverId)
            .orElseThrow { UserNotFoundException.fromId(request.receiverId) }

        val friendship = receiver.receivedFriendships.find { it.id == request.friendshipId }
            ?: throw FriendshipNotFoundException.fromId(request.friendshipId)

        if (friendship.status != FriendshipStatus.PENDING) {
            throw InvalidFriendshipStatusException(friendship.id, request.receiverId)
        }

        return friendship
    }

    fun deleteFriend(request: DeleteFriendshipRequest) : UUID {
        if (request.userId == request.friendId) {
            throw SelfFriendRequestException(request.userId)
        }

        val friendship = friendshipRepository.findByUsers(request.userId, request.friendId)
            ?: throw FriendshipNotFoundException.fromUserIds(request.userId, request.friendId)

        if (friendship.status != FriendshipStatus.ACCEPTED) {
            throw InvalidFriendshipStatusException(friendship.id, request.userId)
        }

        if (friendship.requester.id != request.userId && friendship.receiver.id != request.userId) {
            throw UnauthorizedFriendshipException(request.userId, friendship.id)
        }

        friendshipRepository.delete(friendship)
        return friendship.id
    }

    fun getFriendList(userId: UUID) : List<UserResponse> {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        return friendshipRepository.findAllAcceptedFriendships(user)
            .map { friendship ->
                val friend = if (friendship.requester.id == userId) {
                    friendship.receiver
                } else {
                    friendship.requester
                }

                UserResponse.of(
                    id = friend.id,
                    name = friend.name,
                    phoneNumber = friend.phoneNumber
                )
            }
    }

    fun getPendingFriendRequests(userId: UUID): List<FriendRequestResponse> {
        val receiver = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        return friendshipRepository.findByReceiverAndStatus(receiver, FriendshipStatus.PENDING)
            .map { friendship ->
                FriendRequestResponse.of(
                    friendshipId = friendship.id,
                    requester = UserResponse.of(
                        id = friendship.requester.id,
                        name = friendship.requester.name,
                        phoneNumber = friendship.requester.phoneNumber
                    ),
                    createdAt = friendship.createdDate
                )
            }
    }
}