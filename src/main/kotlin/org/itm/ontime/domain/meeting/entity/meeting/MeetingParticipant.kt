package org.itm.ontime.domain.meeting.entity.meeting

import jakarta.persistence.*
import org.itm.ontime.domain.payment.entity.PaymentStatus
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity

@Entity
class MeetingParticipant(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    var meeting: Meeting,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val participant: User,

//    @Embedded
//    @Column(nullable = false)
//    @JoinColumn(name = "attendance_id")
//    var attendance: Attendance,
//
//    @Column(nullable = false)
//    var paymentStatus: PaymentStatus = PaymentStatus.NOT_REQUIRED
) : BaseEntity(){
//    fun payLateFee() {
//        paymentStatus = PaymentStatus.PAID
//    }
}