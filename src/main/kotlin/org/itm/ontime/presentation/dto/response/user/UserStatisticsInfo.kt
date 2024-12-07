package org.itm.ontime.presentation.dto.response.user

import org.itm.ontime.domain.user.User

data class UserStatisticsInfo(
    val totalMeetings: Int,
    val totalArrivedMeetings: Int,
    val totalLateMeetings: Int,
    val lateRate : Double,
) {
    companion object{
        @JvmStatic
        fun of(user : User) =
            UserStatisticsInfo(
                totalMeetings = user.totalMeetings,
                totalArrivedMeetings = user.totalArrivedMeetings,
                totalLateMeetings = user.totalLateMeetings,
                lateRate = user.lateRate
            )
    }
}
