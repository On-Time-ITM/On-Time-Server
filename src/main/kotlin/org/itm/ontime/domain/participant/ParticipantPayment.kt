package org.itm.ontime.domain.participant

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.itm.ontime.domain.common.BaseEntity
import java.math.BigDecimal

@Entity
@Table(name = "participant_payments"  )
class ParticipantPayment(
    @Column(nullable = false)
    var lateFee: BigDecimal = BigDecimal(0),

    @Column(nullable = false)
    var participantPaymentStatus: ParticipantPaymentStatus = ParticipantPaymentStatus.PENDING

) : BaseEntity() {
    fun updateLateFee(lateFee: BigDecimal) {
        this.lateFee = lateFee
    }

    fun updatePaymentStatus() {
        this.participantPaymentStatus = ParticipantPaymentStatus.PAID
    }
}