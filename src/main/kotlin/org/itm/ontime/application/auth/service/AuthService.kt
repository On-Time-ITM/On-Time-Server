package org.itm.ontime.application.auth.service

import jakarta.transaction.Transactional
import org.itm.ontime.application.auth.exception.common.InvalidRefreshTokenException
import org.itm.ontime.application.auth.exception.local.DuplicationPhoneNumberException
import org.itm.ontime.application.auth.exception.local.InvalidPasswordException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.auth.entity.RefreshToken
import org.itm.ontime.domain.auth.repository.RefreshTokenRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.infrastructure.security.jwt.JwtTokenProvider
import org.itm.ontime.presentation.auth.request.LoginRequest
import org.itm.ontime.presentation.auth.request.SignUpRequest
import org.itm.ontime.presentation.auth.request.TokenRefreshRequest
import org.itm.ontime.presentation.auth.response.LoginResponse
import org.itm.ontime.presentation.auth.response.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @Transactional
    fun signUp(request: SignUpRequest) : TokenResponse {
        if (userRepository.existsByPhoneNumber(request.phoneNumber)) {
            throw DuplicationPhoneNumberException(request.phoneNumber)
        }

        val user = userRepository.save(User(
            phoneNumber = request.phoneNumber,
            password = passwordEncoder.encode(request.password),
            name = request.name
        ))

        return createTokens(user.id)
    }

    @Transactional
    fun login(request: LoginRequest) : LoginResponse {
        val user = userRepository.findByPhoneNumber(request.phoneNumber)
            ?: throw UserNotFoundException.fromPhoneNumber(request.phoneNumber)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidPasswordException()
        }

        return LoginResponse.of(
            tokenResponse = createTokens(user.id),
            userId = user.id,
            name = user.name,
            phoneNumber = user.phoneNumber,
            tardinessRate = user.tardinessRate
        )
    }

    fun logout(userId: UUID) {
        refreshTokenRepository.deleteByUserId(userId)
    }

    @Transactional
    fun createTokens(userId: UUID) : TokenResponse {

        val existingToken = refreshTokenRepository.findByUserId(userId).orElse(null)

        existingToken?.let {
            refreshTokenRepository.deleteById(it.id)
            refreshTokenRepository.flush() // TODO : flush 성능 문제..
        }

        val accessToken = jwtTokenProvider.createAccessToken(userId)
        val refreshToken = jwtTokenProvider.createRefreshToken(userId)

        refreshTokenRepository.save(
            RefreshToken(
                userId = userId,
                token = refreshToken
            )
        )

        return TokenResponse.of(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = jwtTokenProvider.getAccessTokenValidityInSeconds(),
        )
    }

    @Transactional
    fun refreshToken(request: TokenRefreshRequest): TokenResponse {
        val refreshToken = refreshTokenRepository.findByToken(request.refreshToken)
            .orElseThrow { InvalidRefreshTokenException(request.refreshToken) }

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken)
            throw InvalidRefreshTokenException(request.refreshToken)
        }

        return createTokens(refreshToken.userId)
    }
}