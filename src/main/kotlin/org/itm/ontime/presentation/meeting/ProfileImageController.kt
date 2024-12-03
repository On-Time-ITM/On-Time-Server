package org.itm.ontime.presentation.meeting

import org.itm.ontime.application.meeting.service.ProfileImageService
import org.itm.ontime.presentation.meeting.request.profileImage.ProfileImageRequest
import org.itm.ontime.presentation.meeting.response.profileImage.ProfileImageResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/meeting/profile-image")
class ProfileImageController(
    private val profileImageService: ProfileImageService
) {
    @PostMapping
    suspend fun createProfileImage(
        @RequestBody request: ProfileImageRequest
    ): ResponseEntity<ProfileImageResponse> {
        val response = profileImageService.createProfileImage(request)
        return ResponseEntity.ok(response)
    }

}