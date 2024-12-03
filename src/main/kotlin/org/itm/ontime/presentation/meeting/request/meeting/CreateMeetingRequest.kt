package org.itm.ontime.presentation.meeting.request.meeting

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import org.itm.ontime.domain.meeting.entity.location.Location
import org.itm.ontime.domain.payment.entity.AccountInfo
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Schema(description = "Request to create a meeting")
data class CreateMeetingRequest(
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
    @field:Future
    val meetingDateTime: LocalDateTime,

    @Schema(description = "Meeting location")
    @field:NotNull
    val location: Location,

    @Schema(
        description = "Late fee amount",
        example = "5000"
    )
    @field:NotNull
    @field:Positive
    val lateFee: BigDecimal,

    @Schema(
        description = "Account information",
        example = "{\"accountNumber\": \"1234567890\", \"bankName\": \"Bank of America\"}"
    )
    @field:NotBlank
    val accountInfo: AccountInfo,

    @Schema(
        description = "Host's user ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val hostId: UUID,

    @Schema(
        description = "Participant IDs",
        example = "[\"123e4567-e89b-12d3-a456-426614174000\", \"987fcdeb-51d2-3456-bcde-789012345678\"]"
    )
    @field:NotEmpty
    val participantIds: List<UUID>,

    @Schema(
        description = "Profile image ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    @field:NotBlank
    val profileImageId: UUID
)
