package org.itm.ontime.application.friendship.exception

import org.itm.ontime.global.error.ErrorCode
import org.springframework.http.HttpStatus

enum class FriendshipErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {

    FRIENDSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "Friendship not found"),
    DUPLICATE_FRIEND_REQUEST(HttpStatus.CONFLICT, "F002", "Duplicate friend request"),
    SELF_FRIEND_REQUEST(HttpStatus.BAD_REQUEST, "F003", "Cannot send friend request to yourself"),
    UNAUTHORIZED_FRIENDSHIP_ACTION(HttpStatus.FORBIDDEN, "F004", "Unauthorized friendship action"),
    ALREADY_FRIENDS(HttpStatus.CONFLICT, "F005", "Users are already friends"),
    BLOCKED_USER(HttpStatus.FORBIDDEN, "F006", "User is blocked"),
    INVALID_FRIENDSHIP_STATUS(HttpStatus.BAD_REQUEST, "F007", "Invalid friendship status for requested operation"),

}