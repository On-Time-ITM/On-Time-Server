package org.itm.ontime.domain.participant

import jakarta.persistence.*
import org.itm.ontime.domain.common.Location
import org.itm.ontime.domain.common.BaseEntity

@Entity
@Table(name = "participant_locations")
class ParticipantLocation (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    val participant: Participant,

    @Embedded
    var location: Location
) : BaseEntity() {
    fun updateLocation(location: Location) {
        this.location = location
    }
}
