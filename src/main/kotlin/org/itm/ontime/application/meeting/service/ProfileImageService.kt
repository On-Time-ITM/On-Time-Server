package org.itm.ontime.application.meeting.service

import jakarta.transaction.Transactional
import org.itm.ontime.domain.meeting.entity.ProfileImage
import org.itm.ontime.domain.meeting.repository.ProfileImageRepository
import org.itm.ontime.infrastructure.external.openAI.OpenAIService
import org.itm.ontime.presentation.meeting.request.CreateMeetingRequest
import org.springframework.stereotype.Service

@Service
class ProfileImageService(
    private val profileImageRepository: ProfileImageRepository,
    private val openAIService: OpenAIService
) {
    @Transactional
    fun createProfileImage(request: CreateMeetingRequest): ProfileImage {
        val profileImageUrl = openAIService.createProfileImage(
            request.profileImageRequest
        )

        val profileImage = ProfileImage(
            prompt = request.profileImageRequest.prompt,
            size = request.profileImageRequest.size,
            url = profileImageUrl
        )

        profileImageRepository.save(profileImage)
        return profileImage
    }
}