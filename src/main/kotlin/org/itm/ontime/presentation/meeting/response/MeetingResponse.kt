package org.itm.ontime.presentation.meeting.response

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.meeting.entity.Meeting
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Schema(description = "Response for a meeting")
data class MeetingResponse(
    @Schema(
        description = "Meeting ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val id: UUID,

    @Schema(
        description = "Meeting name",
        example = "Weekly Team Meeting"
    )
    @field:NotBlank
    val name: String,

    @Schema(
        description = "Meeting date and time",
        example = "2024-12-25T15:00:00"
    )
    @field:NotBlank
    val meetingDateTime: LocalDateTime,

    @Schema(description = "Meeting location")
    @field:NotBlank
    val location: Location,

    @Schema(
        description = "Late fee amount",
        example = "5000"
    )
    @field:NotBlank
    val lateFee: BigDecimal,

    @Schema(
        description = "Bank account number",
        example = "01234567890987"
    )
    @field:NotBlank
    val bankAccount: String,

    // TODO : attendance

) {
    companion object {
        @JvmStatic
        fun of(meeting: Meeting) = MeetingResponse(
            meeting.id,
            meeting.name,
            meeting.meetingDateTime,
            meeting.location,
            meeting.lateFee,
            meeting.bankAccount
        )

        fun of(meetings: List<Meeting>): List<MeetingResponse> =
            meetings.map(::of)
    }
}
