package org.itm.ontime.domain.attendance.entity

import jakarta.persistence.*
import org.itm.ontime.global.entity.PrimaryKeyEntity
import org.itm.ontime.domain.meeting.entity.meeting.Meeting
import org.itm.ontime.domain.payment.entity.PaymentStatus
import org.itm.ontime.domain.user.entity.User
import java.time.LocalDateTime

@Entity
class Attendance(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    private val meeting: Meeting,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,

    @Column(nullable = false)
    private val arrivalTime: LocalDateTime
) : PrimaryKeyEntity() {

    @Column(nullable = false)
    private var isLate: Boolean = false

    @Column(nullable = true)
    private var locationImage: String? = null

    @Column(nullable = true)
    private var paymentStatus: PaymentStatus = PaymentStatus.NOT_REQUIRED

}