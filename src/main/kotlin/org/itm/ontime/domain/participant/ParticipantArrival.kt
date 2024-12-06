package org.itm.ontime.domain.participant

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.itm.ontime.domain.common.PrimaryKeyEntity
import java.time.LocalDateTime

@Entity
@Table(name = "participant_arrivals")
class ParticipantArrival(
    @Column(nullable = false) var status: ParticipantArrivalStatus = ParticipantArrivalStatus.NOT_ARRIVED,

    @Column(nullable = true) var arrivedTime: LocalDateTime? = null,

    @Column(nullable = false) var isLate: Boolean = false
) : PrimaryKeyEntity() {
    fun markArrival(arrivedTime: LocalDateTime?, isLate: Boolean = false) {
        this.status = ParticipantArrivalStatus.ARRIVED
        this.arrivedTime = arrivedTime
        this.isLate = isLate
    }

    fun markLate() {
        this.isLate = true
    }
}