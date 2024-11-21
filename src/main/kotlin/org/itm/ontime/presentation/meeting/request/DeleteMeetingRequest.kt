package org.itm.ontime.presentation.meeting.request

import java.util.UUID

data class DeleteMeetingRequest(
    val meetingId: UUID,
    val hostId: UUID
)
