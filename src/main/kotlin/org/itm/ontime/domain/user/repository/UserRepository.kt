package org.itm.ontime.domain.user.repository

import org.itm.ontime.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByPhoneNumber(phoneNumber: String): User?
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    fun findAllById(ids: MutableList<UUID>): MutableList<User>
}