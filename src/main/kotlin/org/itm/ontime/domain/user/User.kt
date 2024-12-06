package org.itm.ontime.domain.user

import jakarta.persistence.*
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.friendship.Friendship
import org.itm.ontime.domain.participant.Participant

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true)
    val phoneNumber: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @OneToMany(mappedBy = "requester")
    val sentFriendships: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "receiver")
    val receivedFriendships: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "participant")
    val participants: MutableList<Participant> = mutableListOf(),

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "statistics_id")
    var statistics: UserStatistics

) : BaseEntity()