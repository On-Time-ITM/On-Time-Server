package org.itm.ontime.presentation.controller.participant

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.service.participant.ParticipantLocationService
import org.itm.ontime.presentation.dto.request.participant.ParticipantLocationsRequest
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationInfo
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/meeting/{meetingId}/location")
@Tag(name = "Participant Location", description = "Participant location management APIs")
class ParticipantLocationController (
    private val participantLocationService: ParticipantLocationService
){

    @Operation(
        summary = "Get participant location",
        description = "Get participant location for a meeting",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Participant location found"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Participant location not found"
            ),
            ApiResponse(
                responseCode = "403",
                description = "Unauthorized"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid meeting ID or participant ID"
            ),
        ]
    )
    @GetMapping("/{participantId}")
    fun getParticipantLocation(
        @PathVariable("meetingId") meetingId: UUID,
        @PathVariable("participantId") participantId: UUID
    ) : ResponseEntity<ParticipantLocationInfo> {
        val response = participantLocationService.getParticipantLocation(meetingId, participantId)
        return ResponseEntity.ok(response)
    }

    @Schema(description = "Get participant location list")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Participant location list"
        ),
        ApiResponse(
            responseCode = "404",
            description = "No participant location found for the meeting"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid meeting ID"
        )
    ])
    @GetMapping
    fun getParticipantLocations(@PathVariable("meetingId") meetingId: UUID) : ResponseEntity<ParticipantLocationsResponse> {
        val response = participantLocationService.getParticipantLocations(meetingId)
        return ResponseEntity.ok(response)
    }

    @Schema(description = "Update participant locations")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Participant locations updated successfully"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input"
        )
    ])
    @PatchMapping
    fun updateParticipantLocations(
        @PathVariable meetingId: UUID,
        @RequestBody @Valid request: ParticipantLocationsRequest
    ) : ResponseEntity<ParticipantLocationsResponse> {
        val response = participantLocationService.updateUserLocations(meetingId, request)
        return ResponseEntity.ok(response)
    }
}