package org.itm.ontime.domain.user.entity

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.itm.ontime.domain.PrimaryKeyEntity
import java.util.*

@Entity
class User(
    @Column(nullable = false, unique = false)
    private val username: String,

    @Column(nullable = false, unique = true)
    private val phoneNumber: String,

    @Column(nullable = true)
    private val kakaoId: String? = null
) : PrimaryKeyEntity() {

    // TODO : Team, Attendance mapping

}