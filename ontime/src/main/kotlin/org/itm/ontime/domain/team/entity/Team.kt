package org.itm.ontime.domain.team.entity

import jakarta.persistence.*
import org.itm.ontime.domain.PrimaryKeyEntity
import org.itm.ontime.domain.meeting.entity.Meeting
import java.time.LocalDateTime


@Entity
class Team(
    @Column(nullable = false)
    private val name: String
) : PrimaryKeyEntity() {

    @OneToMany(mappedBy = "team")
    private val members: MutableList<TeamMember> = mutableListOf()

    @OneToMany(mappedBy = "team")
    private val meetings: MutableList<Meeting> = mutableListOf()

    @Column(nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

}