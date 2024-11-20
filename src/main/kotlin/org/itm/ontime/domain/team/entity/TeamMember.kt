package org.itm.ontime.domain.team.entity

import jakarta.persistence.*
import org.itm.ontime.domain.PrimaryKeyEntity
import org.itm.ontime.domain.user.entity.User
import java.time.LocalDateTime

@Entity
class TeamMember(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private val team: Team,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private val role: TeamRole = TeamRole.MEMBER

) : PrimaryKeyEntity() {

    @Column(nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

}