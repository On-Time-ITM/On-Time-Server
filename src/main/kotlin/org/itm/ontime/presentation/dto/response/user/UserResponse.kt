package org.itm.ontime.presentation.dto.response.user

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.domain.user.User
import java.util.*

@Schema(description = "Response for user search")
data class UserResponse(
    @Schema(
        description = "User ID",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val id: UUID,

    @Schema(
        description = "User name",
        example = "Saeyeon Lim"
    )
    val name: String,

    @Schema(
        description = "Phone number",
        example = "010-1234-5678"
    )
    val phoneNumber: String,

    @Schema(
        description = "User statistics",
        example = "UserStatisticsResponse"
    )
    val statistics: UserStatisticsInfo
) {
    companion object {
        @JvmStatic
        fun of(user: User): UserResponse = UserResponse(
                id = user.id,
                name = user.name,
                phoneNumber = user.phoneNumber,
                statistics = UserStatisticsInfo.of(user.statistics)
            )
    }
}
