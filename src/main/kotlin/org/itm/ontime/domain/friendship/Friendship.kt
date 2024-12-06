package org.itm.ontime.domain.friendship

import jakarta.persistence.*
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.user.User

@Entity
@Table(name = "friendships")
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