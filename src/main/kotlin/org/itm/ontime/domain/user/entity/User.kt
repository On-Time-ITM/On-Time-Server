package org.itm.ontime.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true) val phoneNumber: String,

    @Column(nullable = false) var password: String,

    @Column(nullable = false) var name: String,

    @OneToMany(mappedBy = "requester")
    val sentFriendships: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "receiver")
    val receivedFriendships: MutableList<Friendship> = mutableListOf()
) : BaseEntity() {
    fun addSentFriendship(friendship: Friendship) {
        sentFriendships.add(friendship)
    }

    fun addReceivedFriendship(friendship: Friendship) {
        receivedFriendships.add(friendship)
    }
}