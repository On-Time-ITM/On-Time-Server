package org.itm.ontime.presentation.controller.participant

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.itm.ontime.application.service.participant.ParticipantStatisticsService
import org.itm.ontime.presentation.dto.response.user.UserStatisticsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/meeting/{meetingId}/statistics")
@Tag(name = "Participant Statistics", description = "Participant statistics management APIs")
class ParticipantStatisticsController(
    private val participantStatisticsService: ParticipantStatisticsService // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation  // Replace with actual service implementation
) {
    @Operation(
        summary = "Get participant statistics",
        description = "Get participant statistics for a meeting",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Participant statistics successfully retrieved"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Meeting or participant not found"
            ),
        ]
    )
    @GetMapping
    fun getParticipantStatistics(
        @PathVariable meetingId: UUID
    ) : ResponseEntity<List<UserStatisticsResponse>>{
        val response = participantStatisticsService.getParticipantsStatistics(meetingId)
        return ResponseEntity.ok(response)
    }
}