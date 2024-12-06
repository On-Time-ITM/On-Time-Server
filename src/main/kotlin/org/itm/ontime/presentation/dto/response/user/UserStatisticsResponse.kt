package org.itm.ontime.presentation.dto.response.user

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "Response for user statistics")
data class UserStatisticsResponse(
    @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val userId: UUID,
    @Schema(description = "User statistics", example = "123e4567-e89b-12d3-a456-426614174000")
    val statisticsInfo: UserStatisticsInfo
) {
    companion object {
        fun of(
            userId: UUID,
            statisticsInfo: UserStatisticsInfo
        ) = UserStatisticsResponse(userId, statisticsInfo)
    }
}
