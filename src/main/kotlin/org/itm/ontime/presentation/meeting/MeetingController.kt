package org.itm.ontime.presentation.meeting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.meeting.service.MeetingService
import org.itm.ontime.presentation.meeting.request.CreateMeetingRequest
import org.itm.ontime.presentation.meeting.request.DeleteMeetingRequest
import org.itm.ontime.presentation.meeting.response.MeetingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/meeting")
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
    fun getMeetingList(@PathVariable userId: UUID): ResponseEntity<List<MeetingResponse>> {
        val meetings = meetingService.getMeetingList(userId)
        return ResponseEntity.ok(meetings)
    }

    @Schema(description = "Get meeting")
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

    @Schema(description = "Create meeting")
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

    @Schema(description = "Delete meeting")
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