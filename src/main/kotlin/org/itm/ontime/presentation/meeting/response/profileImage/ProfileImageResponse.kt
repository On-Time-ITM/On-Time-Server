package org.itm.ontime.presentation.meeting.response.profileImage

import com.aallam.openai.api.image.ImageSize
import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.domain.meeting.entity.profileImage.ProfileImage
import java.util.*

@Schema(
    description = "Response for profile image",
    example = """{"model": "dall-e-3", "prompt": "A profile image for a user", "size": "1024x1024", "quality": "standard", "n": 1}"""
)
data class ProfileImageResponse(
    @Schema(
        description = "Id of the profile image",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val id: UUID,

    @Schema(
        description = "Prompt for the model",
        example = "A profile image for a user"
    )
    val prompt: String,

    @Schema(
        description = "Size of the image",
        example = "1024x1024"
    )
    val size: ImageSize?,

) {
    companion object {
        @JvmStatic
        fun of(profileImage: ProfileImage): ProfileImageResponse {
            return ProfileImageResponse(
                id = profileImage.id,
                prompt = profileImage.prompt,
                size = profileImage.size)
        }
    }
}