package org.itm.ontime.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.Getter
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.global.common.BaseEntity

@Getter
@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true) val phoneNumber: String,

    @Column(nullable = true)
    private val kakaoId: String? = null,

    @Column(nullable = false) var password: String,

    @Column(nullable = false) var name: String,

    @OneToMany(mappedBy = "requester")
    val sentFriendRequests: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "receiver")
    val receivedFriendRequests: MutableList<Friendship> = mutableListOf(),

    @Column(nullable = false)
    var tardinessRate: Int = 0
    ) : BaseEntity() {

//    @OneToMany(mappedBy = "user")
//    private val teamMemberships: MutableList<TeamMember> = mutableListOf()
//
//    @OneToMany(mappedBy = "user")
//    private val attendances: MutableList<Attendance> = mutableListOf()

}