package org.itm.ontime.presentation.meeting.response

import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.meeting.entity.Meeting
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class MeetingResponse(
    val id: UUID,
    val name: String,
    val meetingDateTime: LocalDateTime,
    val location: Location,
    val lateFee: BigDecimal,
    val bankAccount: String,
    // TODO : attendance
) {
    companion object {
        fun of(meeting: Meeting) = MeetingResponse(
            id = meeting.id,
            name = meeting.name,
            meetingDateTime = meeting.meetingDateTime,
            location = meeting.location,
            lateFee = meeting.lateFee,
            bankAccount = meeting.bankAccount
        )

        fun of(meetings: List<Meeting>): List<MeetingResponse> =
            meetings.map(::of)
    }
}
