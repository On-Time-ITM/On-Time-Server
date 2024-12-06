package org.itm.ontime.presentation.dto.response.user

import org.itm.ontime.domain.user.UserStatistics

data class UserStatisticsInfo(
    val totalMeetings: Int,
    val totalArrivedMeetings: Int,
    val totalLateMeetings: Int,
    val lateRate : Double,
) {
    companion object{
        @JvmStatic
        fun of(userStatistics: UserStatistics) =
            UserStatisticsInfo(
                totalMeetings = userStatistics.totalMeetings,
                totalArrivedMeetings = userStatistics.totalArrivedMeetings,
                totalLateMeetings = userStatistics.totalLateMeetings,
                lateRate = userStatistics.lateRate
            )
    }
}
