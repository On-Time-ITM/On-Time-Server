package org.itm.ontime.application.meeting.service

//import org.itm.ontime.infrastructure.external.openAI.OpenAIService
import jakarta.transaction.Transactional
import org.itm.ontime.domain.meeting.entity.profileImage.ProfileImage
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.domain.meeting.repository.ProfileImageRepository
import org.itm.ontime.infrastructure.external.openAI.OpenAIClient
import org.itm.ontime.presentation.meeting.request.profileImage.ProfileImageRequest
import org.itm.ontime.presentation.meeting.response.profileImage.ProfileImageResponse
import org.springframework.stereotype.Service

@Service
class ProfileImageService(
    private val profileImageRepository: ProfileImageRepository,
    private val meetingRepository: MeetingRepository,
    private val openAIService: OpenAIClient
) {
    @Transactional
    suspend fun createProfileImage(
        request: ProfileImageRequest
    ): ProfileImageResponse {
        val profileImageUrl = openAIService.createProfileImage(request)

        val profileImage = ProfileImage(
            prompt = request.prompt,
            size = request.size,
            url = profileImageUrl
        )

        profileImageRepository.save(profileImage)
        return ProfileImageResponse.of(profileImage)
    }
}