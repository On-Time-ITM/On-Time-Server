package org.itm.ontime.presentation.meeting.response

import io.swagger.v3.oas.annotations.media.Schema
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
    val size: String = "1024x1024",

) {
    companion object {
        @JvmStatic
        fun of(id: UUID, prompt: String, size: String): ProfileImageResponse {
            return ProfileImageResponse(id, prompt, size)
        }
    }
}