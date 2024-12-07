package org.itm.ontime.application.service.user

import org.itm.ontime.application.exception.user.UserNotFoundException
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.itm.ontime.presentation.dto.response.user.UserStatisticsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getUserStatistics(userId: UUID): UserStatisticsResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        return UserStatisticsResponse.of(user)
    }

    @Transactional
    fun updateUserStatistics(userId: UUID, isLate: Boolean) {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.fromId(userId) }

        user.updateStatistics(isLate)
    }

}