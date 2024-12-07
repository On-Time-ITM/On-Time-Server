package org.itm.ontime.presentation.dto.response.user

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.domain.user.User
import java.util.*

@Schema(description = "Response for user statistics")
data class UserStatisticsResponse(
    @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val userId: UUID,
    @Schema(
        description = "Statistics Information of a user",
        example = "{\"totalMeetings\": 10, \"totalArrivedMeetings\": 8, \"totalLateMeetings\": 2, \"lateRate\": 20.0}")
    val statisticsInfo: UserStatisticsInfo
) {
    companion object {
        @JvmStatic
        fun of(user: User) =
            UserStatisticsResponse(
                userId = user.id,
                statisticsInfo = UserStatisticsInfo.of(user)
            )
    }
}
