package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.*
import org.itm.ontime.domain.attendance.entity.Attendance
import org.itm.ontime.domain.location.entity.Location
import org.itm.ontime.domain.location.entity.UserLocation
import org.itm.ontime.domain.payment.entity.AccountInfo
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.global.entity.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "meeting")
class Meeting(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val meetingDateTime: LocalDateTime,

    @Column(nullable = false)
    val location: Location,

    @Column(nullable = false)
    val lateFee: BigDecimal,

    @Column(nullable = false)
    val accountInfo: AccountInfo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    val host: User,

    @OneToMany(mappedBy = "meeting", cascade = [CascadeType.ALL], orphanRemoval = true)
    val participants: MutableList<User> = mutableListOf(),

    @OneToMany(mappedBy = "meeting", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLocations : MutableList<UserLocation> = mutableListOf(),

    @OneToMany(mappedBy = "meeting")
    val attendances: MutableList<Attendance> = mutableListOf(),

    @Column(nullable = true) var qrCode: String ?= null,

    @Column(nullable = false)
    @JoinColumn(name = "profile_image_id") val profileImage: ProfileImage

) : BaseEntity() {
    fun addUserLocation(userLocation: UserLocation) {
        userLocations.add(userLocation)
        userLocation.meeting = this
    }

    fun addQRCode(qrCode: String) {
        this.qrCode = qrCode
    }

    fun hasQRCode() = qrCode?.isNotEmpty() == true

}