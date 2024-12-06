package org.itm.ontime.domain.participant

import jakarta.persistence.*
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.meeting.Meeting
import org.itm.ontime.domain.user.User

@Entity
@Table(name = "participants")
class Participant(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    var meeting: Meeting,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val participant: User,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "participant_attendance_id")
    var arrival: ParticipantArrival = ParticipantArrival(),

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "participant_payment_id")
    var participantPayment: ParticipantPayment = ParticipantPayment()

    ) : BaseEntity()