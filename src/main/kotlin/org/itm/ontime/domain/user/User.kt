package org.itm.ontime.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.friendship.Friendship
import org.itm.ontime.domain.participant.Participant

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true)
    val phoneNumber: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @OneToMany(mappedBy = "requester")
    val sentFriendships: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "receiver")
    val receivedFriendships: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "participant")
    val participants: MutableList<Participant> = mutableListOf(),

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