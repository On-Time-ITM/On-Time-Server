package org.itm.ontime.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.itm.ontime.domain.common.BaseEntity

@Entity
@Table(name = "user_statistics")
class UserStatistics(
    @Column(nullable = false)
    var totalMeetings: Int = 0,

    @Column(nullable = false)
    var totalArrivedMeetings: Int = 0,

    @Column(nullable = false)
    var totalLateMeetings: Int = 0,

    @Column(nullable = false)
    var lateRate : Double = 0.0,
) : BaseEntity() {
    fun updateStatistics(isLate: Boolean) {
        if (isLate) {
            totalLateMeetings++
        }
        totalMeetings++
        totalArrivedMeetings++
        calculateLateRate()
    }
    fun calculateLateRate() {
        lateRate = if (totalMeetings > 0) {
            (totalLateMeetings.toDouble() / totalMeetings) * 100
        } else 0.0
    }
}