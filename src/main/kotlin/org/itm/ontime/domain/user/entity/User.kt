package org.itm.ontime.domain.user.entity

import jakarta.persistence.*
import org.itm.ontime.domain.friendship.entity.Friendship
import org.itm.ontime.domain.meeting.entity.Meeting
import org.itm.ontime.domain.meeting.entity.MeetingParticipant
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true) val phoneNumber: String,

    @Column(nullable = true)
    private val kakaoId: String? = null,

    @Column(nullable = false) var password: String,

    @Column(nullable = false) var name: String,

    @OneToMany(mappedBy = "requester", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sentFriendRequests: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "receiver", cascade = [CascadeType.ALL], orphanRemoval = true)
    val receivedFriendRequests: MutableList<Friendship> = mutableListOf(),

    @OneToMany(mappedBy = "host", cascade = [CascadeType.ALL], orphanRemoval = true)
    val hostedMeetings: MutableList<Meeting> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val participatedMeetings: MutableList<MeetingParticipant> = mutableListOf(),

    @Column(nullable = false)
    var tardinessRate: Int = 0

    ) : BaseEntity() {

}