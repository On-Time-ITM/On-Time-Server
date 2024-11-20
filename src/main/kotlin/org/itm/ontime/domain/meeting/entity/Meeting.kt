package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.team.entity.Team
import org.itm.ontime.global.common.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class Meeting(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private val team: Team,

    @Column(nullable = false)
    private val meetingDateTime: LocalDateTime,

    @Column(nullable = false)
    private val location: String,

    @Column(nullable = false)
    private val lateFee: BigDecimal,

    @Column(nullable = false)
    private val bankAccountNumber: String
) : BaseEntity() {

    @OneToMany(mappedBy = "meeting")
    private val attendances: MutableList<Attendance> = mutableListOf()

    @Column(nullable = true)
    private var qrCode: String ?= null

}