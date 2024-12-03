package org.itm.ontime.presentation.meeting.request.profileImage

import com.aallam.openai.api.image.ImageSize
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank


@Schema(description = "Request for profile image")
data class ProfileImageRequest(
    @Schema(
        description = "Title(prompt) of the image",
        example = "Profile Image"
    )
    @field:NotBlank
    val prompt: String,

    @Schema(
        description = "Size of the image",
        example = ""
    )
    @field:Nullable
    val size: ImageSize?
)