package org.itm.ontime.presentation.friendship

import org.itm.ontime.application.friendship.service.FriendshipService
import org.itm.ontime.presentation.friendship.request.FriendshipAcceptRequest
import org.itm.ontime.presentation.friendship.request.FriendshipDeleteRequest
import org.itm.ontime.presentation.friendship.request.FriendshipRequest
import org.itm.ontime.presentation.friendship.response.FriendRequestResponse
import org.itm.ontime.presentation.friendship.response.FriendResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/friendship")
class FriendShipController(
   private val friendshipService: FriendshipService
) {
    @PostMapping
    fun sendFriendRequest(
        @RequestBody request: FriendshipRequest
    ) : ResponseEntity<Unit> {
        friendshipService.sendFriendRequest(request)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/accept")
    fun acceptFriendRequest(
        @RequestBody request: FriendshipAcceptRequest
    ) : ResponseEntity<Unit> {
        friendshipService.acceptFriendRequest(request)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/reject")
    fun rejectFriendRequest(
        @RequestBody request: FriendshipAcceptRequest
    ) : ResponseEntity<Unit> {
        friendshipService.rejectFriendRequest(request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{userId}")
    fun deleteFriend(
        @RequestBody request: FriendshipDeleteRequest
    ) : ResponseEntity<Unit> {
        friendshipService.deleteFriend(request)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{userId}")
    fun getFriendList(
        @PathVariable userId: UUID
    ) : ResponseEntity<List<FriendResponse>> {
        val friends = friendshipService.getFriendList(userId)
        return ResponseEntity.ok(friends)
    }

    @GetMapping("/request/{userId}")
    fun getFriendRequests(
        @PathVariable userId: UUID
    ) : ResponseEntity<List<FriendRequestResponse>> {
        val requests = friendshipService.getReceivedFriendRequests(userId)
        return ResponseEntity.ok(requests)
    }
}