package org.itm.ontime.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import org.itm.ontime.domain.Attendance.entity.Attendance
import org.itm.ontime.domain.PrimaryKeyEntity
import org.itm.ontime.domain.team.entity.TeamMember
import org.itm.ontime.global.common.BaseEntity

@Entity
class User(
    @Column(nullable = false, unique = false)
    private val username: String,

    @Column(nullable = false, unique = true)
    private val phoneNumber: String,

    @Column(nullable = true)
    private val kakaoId: String? = null
) : BaseEntity() {

    @OneToMany(mappedBy = "user")
    private val teamMemberships: MutableList<TeamMember> = mutableListOf()

    @OneToMany(mappedBy = "user")
    private val attendances: MutableList<Attendance> = mutableListOf()

}