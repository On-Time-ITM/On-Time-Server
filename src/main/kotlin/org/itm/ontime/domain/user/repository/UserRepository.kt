package org.itm.ontime.domain.user.repository

import org.itm.ontime.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByPhoneNumber(phoneNumber: String): User?
    fun findAllById(map: List<UUID>): MutableList<User>
}