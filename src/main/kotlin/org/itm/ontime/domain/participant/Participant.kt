package org.itm.ontime.domain.participant

import jakarta.persistence.*
import org.itm.ontime.domain.common.BaseEntity
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.meeting.Meeting
import org.itm.ontime.domain.user.User

@Entity
@Table(name = "participants")
class Participant(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meetings_id")
    var meeting: Meeting,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val participant: User,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "latitude", column = Column(nullable = true)),
        AttributeOverride(name = "longitude", column = Column(nullable = true)),
        AttributeOverride(name = "address", column = Column(nullable = true))
    )
    var location: Location? = null,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "participant_attendance_id")
    var arrival: ParticipantArrival = ParticipantArrival(),
    ) : BaseEntity() {
        fun updateLocation(location: Location) {
            this.location = location
        }
    }