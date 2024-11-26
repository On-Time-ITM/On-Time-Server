package org.itm.ontime.presentation.meeting.request

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
        example = "1024x1024"
    )
    @field:Nullable
    val size: String
)
