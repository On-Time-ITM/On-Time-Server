package org.itm.ontime.application.service.user

import org.itm.ontime.application.exception.user.UserNotFoundException
import org.itm.ontime.application.exception.user.UserStatisticsNotFoundException
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.itm.ontime.infrastructure.repository.user.UserStatisticsRepository
import org.itm.ontime.presentation.dto.response.user.UserStatisticsInfo
import org.itm.ontime.presentation.dto.response.user.UserStatisticsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserStatisticService(
    private val userRepository: UserRepository,
    private val userStatisticsRepository: UserStatisticsRepository
) {
    @Transactional(readOnly = true)
    fun getUserStatistics(userId: UUID): UserStatisticsResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        val userStatistics = userStatisticsRepository.findByUserId(userId)
            ?: throw UserStatisticsNotFoundException(user.id)

        return UserStatisticsResponse.of(
            user.id,
            UserStatisticsInfo.of(userStatistics)
        )
    }

    @Transactional
    fun updateUserStatistics(userId: UUID, isLate: Boolean) {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        val userStatistics = userStatisticsRepository.findByUserId(userId)
           ?: throw UserStatisticsNotFoundException(user.id)

        userStatistics.updateStatistics(isLate)
    }
}