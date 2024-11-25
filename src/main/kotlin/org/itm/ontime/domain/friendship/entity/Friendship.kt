package org.itm.ontime.domain.friendship.entity

import jakarta.persistence.*
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "friendship")
class Friendship(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    var requester : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    var receiver : User,

    @Enumerated(EnumType.STRING)
    var status : FriendshipStatus = FriendshipStatus.PENDING,
) : BaseEntity() {
    fun accept() {
        this.status = FriendshipStatus.ACCEPTED
    }
}