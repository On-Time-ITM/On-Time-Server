package org.itm.ontime.application.meeting.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import java.util.*

@Schema(description = "Exception thrown when user is not a friend of the meeting host")
class NonFriendInviteException(
    @Schema(description = "Host ID", example = "550e8400-e29b-41d4-a716-446655440000")
    val hostId: UUID,
    @Schema(description = "Participant ID", example = "550e8400-e29b-41d4-a716-446655440000")
    val participantIds: List<UUID>
) : BusinessException(
    MeetingErrorCode.NON_FRIEND_INVITE,
    "User $participantIds is not a friend of user $hostId"
)