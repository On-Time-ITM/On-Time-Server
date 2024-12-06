package org.itm.ontime.presentation.controller.meeting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.service.meeting.MeetingService
import org.itm.ontime.presentation.dto.request.meeting.CreateMeetingRequest
import org.itm.ontime.presentation.dto.request.meeting.DeleteMeetingRequest
import org.itm.ontime.presentation.dto.response.meeting.MeetingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/meeting")
@Tag(name = "Meeting", description = "Meeting management APIs")
class MeetingController(
    private val meetingService: MeetingService
) {

    @Operation(
        summary = "Get meeting list",
        description = "Get a list of meetings for a user"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Meeting list"
        ),
        ApiResponse(
            responseCode = "404",
            description = "No meeting found for the user"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid user ID"
        )
    ])
    @GetMapping("/list/{userId}")
    fun getUserMeetings(@PathVariable userId: UUID): ResponseEntity<List<MeetingResponse>> {
        val meetings = meetingService.getMeetings(userId)
        return ResponseEntity.ok(meetings)
    }

    @Operation(
        summary = "Get a meeting",
        description = "Get details of a specific meeting"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Meeting details"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Meeting not found"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid meeting ID"
        )
    ])
    @GetMapping("/{meetingId}")
    fun getMeeting(@PathVariable meetingId: UUID): ResponseEntity<MeetingResponse> {
        val meeting = meetingService.getMeeting(meetingId)
        return ResponseEntity.ok(meeting)
    }

    @Operation(
        summary = "Create meeting",
        description = "Create a new meeting"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Meeting ID"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input"
        )
    ])
    @PostMapping
    fun createMeeting(@RequestBody @Valid request: CreateMeetingRequest): ResponseEntity<UUID> {
        val meetingId = meetingService.createMeeting(request)
        return ResponseEntity.ok(meetingId)
    }

    @Operation(
        summary = "Delete meeting",
        description = "Delete a meeting"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Meeting ID"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input"
        )
    ])

    @DeleteMapping
    fun deleteMeeting(@RequestBody @Valid request: DeleteMeetingRequest): ResponseEntity<UUID> {
        val meetingId = meetingService.deleteMeeting(request)
        return ResponseEntity.ok(meetingId)
    }
}