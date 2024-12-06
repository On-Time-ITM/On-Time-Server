package org.itm.ontime.domain.meeting

import jakarta.persistence.*
import org.itm.ontime.domain.common.AccountInfo
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.participant.Participant
import org.itm.ontime.domain.user.User
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "meetings")
class Meeting(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val meetingDateTime: LocalDateTime,

    @Embedded
    @Column(nullable = false)
    val location: Location,

    @Column(nullable = false)
    val lateFee: BigDecimal,

    @Column(nullable = false)
    val accountInfo: AccountInfo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    val host: User,

    @OneToMany(mappedBy = "meeting", cascade = [CascadeType.ALL], orphanRemoval = true)
    val participants: MutableList<Participant> = mutableListOf(),

    @Column(nullable = true) var qrCode: String ?= null,

    @Column(nullable = false)
    val profileImage: String

) : BaseEntity() {

    fun addQRCode(qrCode: String) {
        this.qrCode = qrCode
    }

    fun hasQRCode() = qrCode?.isNotEmpty() == true

}