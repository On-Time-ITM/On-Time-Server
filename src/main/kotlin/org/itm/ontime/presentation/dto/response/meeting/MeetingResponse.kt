package org.itm.ontime.presentation.dto.response.meeting

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.domain.common.AccountInfo
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.meeting.Meeting
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Schema(description = "Response for a meeting")
data class MeetingResponse(
    @Schema(
        description = "Meeting ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val id: UUID,

    @Schema(
        description = "Meeting name",
        example = "Weekly Team Meeting"
    )
    val name: String,

    @Schema(
        description = "Meeting date and time",
        example = "2024-12-25T15:00:00"
    )
    val meetingDateTime: LocalDateTime,

    @Schema(
        description = "Meeting location",
        example = "[{\"location\": {\"latitude\": 37.7749, \"longitude\": -122.4194}, \"address\": Frontier}]"
    )
    val location: Location,

    @Schema(
        description = "Late fee amount",
        example = "5000"
    )
    val lateFee: BigDecimal,

    @Schema(
        description = "Bank account information",
        example = "{\"accountNumber\": \"1234567890\", \"bankName\": \"Bank of America\"}"
    )
    val accountInfo: AccountInfo,

    @Schema(
        description = "Profile image",
        example = "kewjfpqofjofjksldjkdjflwekflwejflqwjfek...."
    )
    val profileImage: String,

    @Schema(
        description = "Participant count",
        example = "3"
    )
    val participantCount: Int

) {
    companion object {
        @JvmStatic
        fun of(meeting: Meeting) =
            MeetingResponse(
                meeting.id,
                meeting.name,
                meeting.meetingDateTime,
                meeting.location,
                meeting.lateFee,
                meeting.accountInfo,
                meeting.profileImage,
                meeting.participantCount
            )

        fun of(meetings: List<Meeting>): List<MeetingResponse> = meetings.map(Companion::of)
    }
}
