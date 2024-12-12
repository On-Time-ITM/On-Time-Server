package org.itm.ontime.presentation.controller.participant

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.service.participant.ParticipantLocationService
import org.itm.ontime.domain.common.Location
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

    @Operation(
        summary = "Get participant location list",
        description = "Get participant location list for a meeting",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Participant location list found"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Participant location list not found"
            ),
            ApiResponse(
                responseCode = "403",
                description = "Unauthorized"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid meeting ID"
            ),
        ]
    )
    @GetMapping
    fun getParticipantLocations(@PathVariable("meetingId") meetingId: UUID) : ResponseEntity<ParticipantLocationsResponse> {
        val response = participantLocationService.getParticipantLocations(meetingId)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Update participant location list",
        description = "Update participant location list for a meeting",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Participant location list updated successfully"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid input"
            )
        ]
    )
    @PatchMapping
    fun updateParticipantLocations(
        @PathVariable meetingId: UUID,
        @RequestBody @Valid request: ParticipantLocationsRequest
    ) : ResponseEntity<ParticipantLocationsResponse> {
        val response = participantLocationService.updateParticipantLocations(meetingId, request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Update participant location",
        description = "Update participant location for a meeting",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Participant location updated successfully"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid input"
            )
        ]
    )
    @PatchMapping("/{participantId}")
    fun updateParticipantLocation(
        @PathVariable meetingId: UUID,
        @PathVariable participantId: UUID,
        @RequestBody @Valid participantLocation: Location
    ) : ResponseEntity<ParticipantLocationInfo> {
        val response = participantLocationService.updateParticipantLocation(meetingId, participantId, participantLocation)
        return ResponseEntity.ok(response)
    }
}