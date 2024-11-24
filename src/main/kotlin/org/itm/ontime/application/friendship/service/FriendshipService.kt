package org.itm.ontime.application.friendship.service

import org.itm.ontime.application.friendship.exception.*
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.friendship.entity.FriendshipStatus
import org.itm.ontime.domain.friendship.repository.FriendshipRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.friendship.request.FriendshipAcceptRequest
import org.itm.ontime.presentation.friendship.request.FriendshipDeleteRequest
import org.itm.ontime.presentation.friendship.request.FriendshipRequest
import org.itm.ontime.presentation.friendship.response.FriendRequestResponse
import org.itm.ontime.presentation.friendship.response.FriendResponse
import org.itm.ontime.presentation.user.request.UserSearchRequest
import org.itm.ontime.presentation.user.response.UserSearchResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendshipService (
    private val userRepository: UserRepository,
    private val friendshipRepository: FriendshipRepository
){
    fun searchFriendByPhoneNumber(request: UserSearchRequest) : UserSearchResponse {
        val user = userRepository.findByPhoneNumber(request.phoneNumber)
            ?: throw UserNotFoundException.fromPhoneNumber(request.phoneNumber)

        return UserSearchResponse.of(
            id = user.id,
            name = user.name,
            phoneNumber = user.phoneNumber
        )
    }

    fun sendFriendRequest(request: FriendshipRequest) : UUID {
        val requester = userRepository.findById(request.requesterId)
            .orElseThrow{ UserNotFoundException.fromId(request.requesterId) }

        val receiver = userRepository.findByPhoneNumber(request.receiverPhoneNumber)
            ?: throw UserNotFoundException.fromPhoneNumber(request.receiverPhoneNumber)

        if (isValidateRequest(requester, receiver)) {
            throw AlreadyFriendsException(requester.id, receiver.id)
        }

        if (isValidateRequest(requester, receiver, FriendshipStatus.PENDING)) {
            throw DuplicateFriendRequestException(requester.id, receiver.id)
        }

        val friendship = Friendship(requester, receiver)
        requester.sentFriendRequests.add(friendship)
        receiver.receivedFriendRequests.add(friendship)

        friendshipRepository.save(friendship)
        return friendship.id
    }

    private fun isValidateRequest(requester: User, receiver: User, status: FriendshipStatus = FriendshipStatus.ACCEPTED) =
        requester.sentFriendRequests.any { it.receiver.id == receiver.id && it.status == status } ||
                requester.receivedFriendRequests.any { it.requester.id == receiver.id && it.status == status }

    fun acceptFriendRequest(request: FriendshipAcceptRequest) : UUID {
        val friendship = getValidFriendship(request)
        friendship.accept()

        friendshipRepository.save(friendship)
        return friendship.id
    }

    fun rejectFriendRequest(request: FriendshipAcceptRequest) : UUID {
        val friendship = getValidFriendship(request)

        friendshipRepository.delete(friendship)
        return friendship.id
    }

    private fun getValidFriendship(request: FriendshipAcceptRequest) : Friendship {
        val receiver = userRepository.findById(request.receiverId)
            .orElseThrow { UserNotFoundException.fromId(request.receiverId) }

        val friendship = receiver.receivedFriendRequests.find { it.id == request.friendshipId }
            ?: throw FriendshipNotFoundException.fromId(request.friendshipId)

        if (friendship.status != FriendshipStatus.PENDING) {
            throw InvalidFriendshipStatusException(friendship.id, request.receiverId)
        }

        return friendship
    }

    fun deleteFriend(request: FriendshipDeleteRequest) : UUID {
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

    fun getFriendList(userId: UUID) : List<FriendResponse> {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        return friendshipRepository.findAllAcceptedFriendships(user)
            .map { friendship ->
                val friend = if (friendship.requester.id == userId) {
                    friendship.receiver
                } else {
                    friendship.requester
                }

                FriendResponse.of(
                    friend.id,
                    friend.phoneNumber,
                    friend.name,
                    friend.tardinessRate
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
                    requester = FriendResponse.of(
                        id = friendship.requester.id,
                        phoneNumber = friendship.requester.phoneNumber,
                        name = friendship.requester.name,
                        tardinessRate = friendship.requester.tardinessRate
                    ),
                    createdAt = friendship.createdDate
                )
            }
    }
}