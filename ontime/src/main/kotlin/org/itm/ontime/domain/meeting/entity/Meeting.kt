package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.Attendance.entity.Attendance
import org.itm.ontime.domain.PrimaryKeyEntity
import org.itm.ontime.domain.team.entity.Team
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

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
) : PrimaryKeyEntity() {

    @OneToMany(mappedBy = "meeting")
    private val attendances: MutableList<Attendance> = mutableListOf()

    @Column(nullable = true)
    private var qrCode: String ?= null
    // TODO : private var 이 아닌 ...
    // TODO : qrCode를 무조건 하나 생성하도록 해서 Non-nullable 로 하는 건?

}