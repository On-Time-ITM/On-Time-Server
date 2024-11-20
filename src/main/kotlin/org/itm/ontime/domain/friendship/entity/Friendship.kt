package org.itm.ontime.domain.friendship.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.common.BaseEntity

@Entity
@Getter
@Table(name = "friendships")
class Friendship(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    var requester : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    var receiver : User,

    @Enumerated(EnumType.STRING)
    var status : FriendshipStatus,
) : BaseEntity() {
}