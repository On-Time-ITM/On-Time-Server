package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.common.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class Meeting(

//    @OneToMany(mappedBy = "team")
//    private val members: MutableList<User> = mutableListOf(),
//
//    @Column(nullable = false)
//    private val meetingDateTime: LocalDateTime,
//
//    @Column(nullable = false)
//    private val latitude: String,
//
//    @Column(nullable = false)
//    private val longitude: String,
//
//    @Column(nullable = false)
//    private val lateFee: BigDecimal,
//
//    @Column(nullable = false)
//    private val bankAccount: String,
//
//    @OneToMany(mappedBy = "meeting")
//    private val attendances: MutableList<Attendance> = mutableListOf()

) : BaseEntity() {

    @Column(nullable = true)
    private var qrCode: String ?= null

}