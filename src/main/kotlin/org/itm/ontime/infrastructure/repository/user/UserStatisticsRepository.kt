package org.itm.ontime.infrastructure.repository.user

import org.itm.ontime.domain.user.UserStatistics
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserStatisticsRepository : JpaRepository<UserStatistics, UUID> {
    fun findByUserId(userId: UUID): UserStatistics?
}