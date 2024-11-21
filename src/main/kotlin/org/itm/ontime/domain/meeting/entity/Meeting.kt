package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "meeting")
class Meeting(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val meetingDateTime: LocalDateTime,

    @Column(nullable = false)
    val location: Location,

    @Column(nullable = false)
    val lateFee: BigDecimal,

    @Column(nullable = false)
    val bankAccount: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    val host: User,

    @OneToMany(mappedBy = "meeting")
    val participants: MutableList<MeetingParticipant> = mutableListOf(),

    @OneToMany(mappedBy = "meeting")
    val attendances: MutableList<Attendance> = mutableListOf()

) : BaseEntity() {

    @Column(nullable = true)
    private var qrCode: String ?= null

    @Column(nullable = true)
    private var profileImage: String ?= null

}