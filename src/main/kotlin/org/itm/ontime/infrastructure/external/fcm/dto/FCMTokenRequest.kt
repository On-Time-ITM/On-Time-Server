package org.itm.ontime.infrastructure.external.fcm.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

@Schema(description = "FCM token information")
data class FCMTokenRequest(
    @Schema(description = "User ID", example = "123-234j3-2324")
    @field:NotBlank
    val userId: UUID,
    @Schema(description = "Token", example = "kfjsdf20f2sldfk23krjsl")
    @field:NotBlank
    val token: String,
)
