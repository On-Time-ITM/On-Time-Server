package org.itm.ontime.presentation.meeting

import org.itm.ontime.application.meeting.service.MeetingService
import org.itm.ontime.presentation.meeting.request.CreateMeetingRequest
import org.itm.ontime.presentation.meeting.request.DeleteMeetingRequest
import org.itm.ontime.presentation.meeting.response.MeetingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/meeting")
class MeetingController(
    private val meetingService: MeetingService
) {

    @GetMapping("/{meetingId}")
    fun getMeeting(@PathVariable meetingId: UUID): ResponseEntity<MeetingResponse> {
        val meeting = meetingService.getMeeting(meetingId)
        return ResponseEntity.ok(meeting)
    }

    @GetMapping("/list/{userId}")
    fun getMeetingList(@PathVariable userId: UUID): ResponseEntity<List<MeetingResponse>> {
        val meetings = meetingService.getMeetingList(userId)
        return ResponseEntity.ok(meetings)
    }

    @PostMapping
    fun createMeeting(@RequestBody request: CreateMeetingRequest): ResponseEntity<UUID> {
        val meetingId = meetingService.createMeeting(request)
        return ResponseEntity.ok(meetingId)
    }

    @DeleteMapping
    fun deleteMeeting(@RequestBody request: DeleteMeetingRequest): ResponseEntity<UUID> {
        val meetingId = meetingService.deleteMeeting(request)
        return ResponseEntity.ok(meetingId)
    }
}