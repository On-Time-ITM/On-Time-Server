package org.itm.ontime.presentation.controller.friendship

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.exception.common.ErrorResponse
import org.itm.ontime.application.service.friendship.FriendshipService
import org.itm.ontime.presentation.dto.request.friendship.AcceptFriendshipRequest
import org.itm.ontime.presentation.dto.request.friendship.DeleteFriendshipRequest
import org.itm.ontime.presentation.dto.request.friendship.SendFriendshipRequest
import org.itm.ontime.presentation.dto.response.friendship.FriendRequestResponse
import org.itm.ontime.presentation.dto.response.user.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/friendship")
@Tag(name = "Friendship", description = "Friendship management APIs")
class FriendShipController(
    private val friendshipService: FriendshipService
) {
    @Operation(
        summary = "Search user",
        description = "Search a user before sending a friend request"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "User found successfully",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = UserResponse::class)
            )]
        ),
        ApiResponse(
            responseCode = "404",
            description = "User not found with given phone number",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ErrorResponse::class)
            )]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid phone number format",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ErrorResponse::class)
            )]
        )
    ])
    @GetMapping("/search")
    fun searchFriend(
        @RequestParam phoneNumber: String
    ) : ResponseEntity<UserResponse> {
        val response = friendshipService.searchFriendByPhoneNumber(phoneNumber)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Send friend request",
        description = "Send a friend request to another user"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Friend request sent successfully"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid request or self-friend request",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "404",
            description = "User not found",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "409",
            description = "Friendship already exists",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @PostMapping
    fun sendFriendRequest(
        @RequestBody @Valid request: SendFriendshipRequest
    ) : ResponseEntity<UUID> {
        val requestId = friendshipService.sendFriendRequest(request)
        return ResponseEntity.ok(requestId)
    }

    @Operation(
        summary = "Accept friend request",
        description = "Accept a pending friend request"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Friend request accepted successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Friendship not found",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Unauthorized to accept this request",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid friendship status",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @PatchMapping("/accept")
    fun acceptFriendRequest(
        @RequestBody @Valid request: AcceptFriendshipRequest
    ) : ResponseEntity<UUID> {
        val requestId = friendshipService.acceptFriendRequest(request)
        return ResponseEntity.ok(requestId)
    }

    @Operation(
        summary = "Reject friend request",
        description = "Reject a pending friend request"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Friend request rejected successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Friendship not found",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Unauthorized to reject this request",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid friendship status",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @PatchMapping("/reject")
    fun rejectFriendRequest(
        @RequestBody @Valid request: AcceptFriendshipRequest
    ) : ResponseEntity<UUID> {
        val requestId = friendshipService.rejectFriendRequest(request)
        return ResponseEntity.ok(requestId)
    }

    @Operation(
        summary = "Delete friendship",
        description = "Remove an existing friendship between users"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "204",
            description = "Friendship deleted successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Friendship not found",
            content = [Content(mediaType = "application/json")]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Unauthorized to delete this friendship",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @DeleteMapping("/{userId}")
    fun deleteFriend(
        @RequestBody @Valid request: DeleteFriendshipRequest
    ) : ResponseEntity<Unit> {
        friendshipService.deleteFriend(request)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Get friend list",
        description = "Retrieve list of all accepted friends for a user"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved friend list",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = UserResponse::class))
            )]
        ),
        ApiResponse(
            responseCode = "404",
            description = "User not found",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @GetMapping("/list/{userId}")
    fun getFriendList(
        @Schema(description = "ID of the user to get friends for", example = "123e4567-e89b-12d3-a456-426614174000")
        @PathVariable userId: UUID
    ) : ResponseEntity<List<UserResponse>> {
        val friends = friendshipService.getFriends(userId)
        return ResponseEntity.ok(friends)
    }

    @Operation(
        summary = "Get received friend requests",
        description = "Retrieve list of pending friend requests received by a user"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved friend requests",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = FriendRequestResponse::class))
            )]
        ),
        ApiResponse(
            responseCode = "404",
            description = "User not found",
            content = [Content(mediaType = "application/json")]
        )
    ])
    @GetMapping("/request/{userId}")
    fun getFriendRequests(
        @Schema(description = "ID of the user to get friend requests for", example = "123e4567-e89b-12d3-a456-426614174000")
        @PathVariable userId: UUID
    ) : ResponseEntity<List<FriendRequestResponse>> {
        val requests = friendshipService.getPendingFriendRequests(userId)
        return ResponseEntity.ok(requests)
    }
}