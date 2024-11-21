package org.itm.ontime.presentation.meeting.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.itm.ontime.domain.common.Location
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class CreateMeetingRequest(
    @field:NotBlank
    @Schema(description = "Meeting name", example = "Weekly Team Meeting")
    val name: String,

    @field:NotBlank
    @field:Future
    @Schema(description = "Meeting date and time", example = "2024-12-25T15:00:00")
    val meetingDateTime: LocalDateTime,

    @field:NotNull
    @Schema(description = "Meeting location")
    val location: Location,

    @field:NotNull
    @field:Positive
    @Schema(description = "Late fee amount", example = "5000")
    val lateFee: BigDecimal,

    @field:NotBlank
    @Schema(description = "Bank account number", example = "1234-5678")
    val bankAccount: String,

    // TODO : Swagger schema
    @field:NotNull
    val hostId: UUID,

    @field:NotEmpty
    val participantIds: MutableList<UUID>
)
