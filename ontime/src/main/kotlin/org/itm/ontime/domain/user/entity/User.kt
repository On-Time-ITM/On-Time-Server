package org.itm.ontime.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import lombok.Getter
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.team.entity.TeamMember
import org.itm.ontime.global.common.BaseEntity

@Getter
@Entity
class User(
    @Column(nullable = false, unique = true)
    private val phoneNumber: String,

    @Column(nullable = true)
    private val kakaoId: String? = null,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = false)
    private var name: String,
    ) : BaseEntity() {

    @OneToMany(mappedBy = "user")
    private val teamMemberships: MutableList<TeamMember> = mutableListOf()

    @OneToMany(mappedBy = "user")
    private val attendances: MutableList<Attendance> = mutableListOf()

}