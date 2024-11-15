package org.itm.ontime.application.auth.service

import jakarta.transaction.Transactional
import org.itm.ontime.domain.auth.entity.RefreshToken
import org.itm.ontime.domain.auth.exception.common.InvalidRefreshTokenException
import org.itm.ontime.presentation.auth.response.TokenResponse
import org.itm.ontime.domain.auth.exception.local.DuplicationPhoneNumberException
import org.itm.ontime.domain.auth.exception.local.InvalidPasswordException
import org.itm.ontime.domain.auth.repository.RefreshTokenRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.exception.UserNotFoundException
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.global.security.jwt.JwtTokenProvider
import org.itm.ontime.presentation.auth.request.LoginRequest
import org.itm.ontime.presentation.auth.request.SignUpRequest
import org.itm.ontime.presentation.auth.request.TokenRefreshRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
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
    fun login(request: LoginRequest) : TokenResponse {
        val user = userRepository.findByPhoneNumber(request.phoneNumber)
            ?: throw UserNotFoundException(request.phoneNumber)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidPasswordException()
        }

        return createTokens(user.id)
    }

    fun logout(userId: UUID) {
        refreshTokenRepository.deleteByUserId(userId)
    }

    @Transactional
    fun createTokens(userId: UUID) : TokenResponse {

        refreshTokenRepository.deleteByUserId(userId)

        val accessToken = jwtTokenProvider.createAccessToken(userId)
        val refreshToken = jwtTokenProvider.createRefreshToken(userId)

        refreshTokenRepository.save(
            RefreshToken(
                userId = userId,
                token = refreshToken
            )
        )

        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = jwtTokenProvider.getAccessTokenValidityInSeconds()
        )
    }

    @Transactional
    fun refreshToken(request: TokenRefreshRequest): TokenResponse {
        val refreshToken = refreshTokenRepository.findByToken(request.refreshToken)
            ?: throw InvalidRefreshTokenException(request.refreshToken)

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken)
            throw InvalidRefreshTokenException(request.refreshToken)
        }

        return this.javaClass.getMethod("createTokens", UUID::class.java)
            .invoke(this, refreshToken.userId) as TokenResponse
    }
}