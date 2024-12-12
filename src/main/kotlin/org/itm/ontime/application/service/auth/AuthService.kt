package org.itm.ontime.application.service.auth

import jakarta.transaction.Transactional
import org.itm.ontime.application.exception.auth.AlreadyExistsPhoneNumberException
import org.itm.ontime.application.exception.auth.InvalidPasswordException
import org.itm.ontime.application.exception.auth.InvalidRefreshTokenException
import org.itm.ontime.application.exception.auth.UserAlreadyLogoutException
import org.itm.ontime.application.exception.user.UserNotFoundException
import org.itm.ontime.domain.user.User
import org.itm.ontime.infrastructure.repository.auth.RefreshTokenRepository
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.itm.ontime.infrastructure.security.JwtTokenProvider
import org.itm.ontime.presentation.dto.request.auth.LoginRequest
import org.itm.ontime.presentation.dto.request.auth.SignUpRequest
import org.itm.ontime.presentation.dto.request.auth.TokenRequest
import org.itm.ontime.presentation.dto.response.auth.AuthResponse
import org.itm.ontime.presentation.dto.response.auth.internal.TokenInfo
import org.itm.ontime.presentation.dto.response.user.UserResponse
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
    fun signUp(request: SignUpRequest) : AuthResponse {
        if (userRepository.existsByPhoneNumber(request.phoneNumber)) {
            throw AlreadyExistsPhoneNumberException(request.phoneNumber)
        }

        val user = User(
            phoneNumber = request.phoneNumber,
            password = passwordEncoder.encode(request.password),
            name = request.name,
        )

        userRepository.save(user)

        val tokenInfo = createTokens(user.id)

        return  AuthResponse.of(
            userInfo = UserResponse.of(user),
            tokenInfo = tokenInfo
        )
    }

    @Transactional
    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByPhoneNumber(request.phoneNumber)
            ?: throw UserNotFoundException(request.phoneNumber)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidPasswordException()
        }

        refreshTokenRepository.deleteByUserId(user.id)
        refreshTokenRepository.flush()

        val tokenInfo = createTokens(user.id)

        return AuthResponse.of(
            userInfo = UserResponse.of(user),
            tokenInfo = tokenInfo
        )
    }

    @Transactional
    fun logout(userId: UUID) {
        refreshTokenRepository.deleteByUserId(userId)
    }

    fun createTokens(userId: UUID): TokenInfo {
        val accessToken = jwtTokenProvider.createAccessToken(userId)
        val accessTokenExpiresIn = jwtTokenProvider.getAccessTokenValidityInSeconds()
        val refreshToken = jwtTokenProvider.createRefreshToken(userId)
        refreshTokenRepository.save(refreshToken)
        return TokenInfo.of(
            accessToken = accessToken,
            accessTokenExpiresIn = accessTokenExpiresIn,
            refreshToken = refreshToken
            )
    }

    fun reissue(tokenRequest: TokenRequest): AuthResponse {
        if (!jwtTokenProvider.validateToken(tokenRequest.refreshToken)) {
            throw InvalidRefreshTokenException(tokenRequest.refreshToken)
        }

        val userId = jwtTokenProvider.getUserId(tokenRequest.accessToken)
        val user = userRepository.findById(userId)
            .orElseThrow{ UserNotFoundException.fromId(userId) }

        val refreshToken = refreshTokenRepository.findByUserId(userId)
            ?: throw UserAlreadyLogoutException(userId)

        if (refreshToken.token != tokenRequest.refreshToken) {
            throw InvalidRefreshTokenException(tokenRequest.refreshToken)
        }

        val newAccessToken = jwtTokenProvider.createAccessToken(user.id)
        val newRefreshToken = jwtTokenProvider.createRefreshToken(user.id)

        val tokenInfo = TokenInfo.of(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken,
            accessTokenExpiresIn = Date().time + jwtTokenProvider.getAccessTokenValidityInSeconds()
        )

        return AuthResponse.of(
            userInfo = UserResponse.of(user),
            tokenInfo = tokenInfo
        )
    }
}