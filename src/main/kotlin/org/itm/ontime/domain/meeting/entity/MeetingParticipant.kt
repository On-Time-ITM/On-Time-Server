package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity

@Entity
class MeetingParticipant(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    val meeting: Meeting,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseEntity(){
}