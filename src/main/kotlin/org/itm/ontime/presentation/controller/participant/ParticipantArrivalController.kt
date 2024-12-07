package org.itm.ontime.presentation.controller.participant

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.itm.ontime.application.service.participant.ParticipantArrivalService
import org.itm.ontime.presentation.dto.request.participant.ParticipantArrivalResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api/v1/meeting/{meetingId}/arrival")
@Tag(name = "Arrival", description = "Arrival management APIs")
class ParticipantArrivalController(
    private val participantArrivalService: ParticipantArrivalService
) {

    @Operation(
        summary = "Register arrival",
        description = "Register an arrival for a participant in a meeting"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Participant arrival registered"
            ),
            ApiResponse(
                responseCode = "404",
                description = "No meeting or participant found for the provided IDs"
            )
        ]
    )
    @GetMapping
    fun getParticipantArrivals(
        @PathVariable meetingId: UUID
    ) : ResponseEntity<List<ParticipantArrivalResponse>> {
        val response = participantArrivalService.getParticipantArrivals(meetingId)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Register arrival",
        description = "Register an arrival for a participant in a meeting"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Participant arrival registered"
            ),
            ApiResponse(
                responseCode = "404",
                description = "No meeting or participant found for the provided IDs"
            )
        ]
    )
    @GetMapping("/{participantId}")
    fun getParticipantArrival(
        @PathVariable meetingId: UUID,
        @PathVariable participantId: UUID
    ) : ResponseEntity<ParticipantArrivalResponse> {
        val response = participantArrivalService.getParticipantArrival(meetingId, participantId)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Register arrival",
        description = "Register an arrival for a participant in a meeting"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Participant arrival registered"
            ),
            ApiResponse(
                responseCode = "404",
                description = "No meeting or participant found for the provided IDs"
            )
        ]
    )
    @PatchMapping("/{participantId}")
    fun markArrivalInfo(
        @PathVariable meetingId: UUID,
        @PathVariable participantId: UUID,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") arrivalTime: LocalDateTime
    ) : ResponseEntity<ParticipantArrivalResponse> {
        val response = participantArrivalService.markArrival(meetingId, participantId, arrivalTime)
        return ResponseEntity.ok(response)
    }
}