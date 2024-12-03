package org.itm.ontime.infrastructure.external.openAI

import com.aallam.openai.api.exception.InvalidRequestException
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import org.itm.ontime.application.meeting.exception.DalleAPIBillingLimitException
import org.itm.ontime.application.meeting.exception.DalleAPIErrorException
import org.itm.ontime.application.meeting.exception.ImageGenerateFailedException
import org.itm.ontime.presentation.meeting.request.profileImage.ProfileImageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OpenAIClient(
    @Value("\${openai.key}")
    private val key : String
) {
    private val openAI = OpenAI(key)
    suspend fun createProfileImage(request: ProfileImageRequest): String {
        return try {
            val imageUrls = openAI.imageURL(
                creation = ImageCreation(
                    prompt = request.prompt,
                    model = ModelId("dall-e-2"),
                    n = 1,
                    size = request.size
                )
            )
            imageUrls.firstOrNull()?.url
                ?: throw ImageGenerateFailedException()
        } catch (e: InvalidRequestException) {
            when {
                e.message?.contains("billing") == true ->
                    throw DalleAPIBillingLimitException()
                else -> throw DalleAPIErrorException()
            }
        }
    }
}