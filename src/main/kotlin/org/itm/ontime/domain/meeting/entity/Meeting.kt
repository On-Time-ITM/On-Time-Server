package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.location.entity.Location
import org.itm.ontime.global.common.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "meeting")
class Meeting(

    @Column(nullable = false)
    private val name: String,

    @Column(nullable = false)
    private val meetingDateTime: LocalDateTime,

    @Column(nullable = false)
    private val location: Location,

    @Column(nullable = false)
    private val lateFee: BigDecimal,

    @Column(nullable = false)
    private val bankAccount: String,

    @OneToMany(mappedBy = "meeting")
    private val attendances: MutableList<Attendance> = mutableListOf()

) : BaseEntity() {

    @Column(nullable = true)
    private var qrCode: String ?= null

    @Column(nullable = true)
    private var profileImage: String ?= null

}