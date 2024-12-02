package org.itm.ontime.application.auth.service

import jakarta.transaction.Transactional
import org.itm.ontime.application.auth.exception.common.InvalidRefreshTokenException
import org.itm.ontime.application.auth.exception.common.UserAlreadyLogoutException
import org.itm.ontime.application.auth.exception.local.AlreadyExistsPhoneNumberException
import org.itm.ontime.application.auth.exception.local.InvalidPasswordException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.auth.repository.RefreshTokenRepository
import org.itm.ontime.domain.user.entity.User
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.infrastructure.security.jwt.JwtTokenProvider
import org.itm.ontime.presentation.auth.request.LoginRequest
import org.itm.ontime.presentation.auth.request.SignUpRequest
import org.itm.ontime.presentation.auth.response.AuthResponse
import org.itm.ontime.presentation.auth.response.TokenInfo
import org.itm.ontime.presentation.user.request.TokenRequest
import org.itm.ontime.presentation.user.response.UserResponse
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
    fun signUp(request: SignUpRequest) : UserResponse {
        if (userRepository.existsByPhoneNumber(request.phoneNumber)) {
            throw AlreadyExistsPhoneNumberException(request.phoneNumber)
        }

        val user = userRepository.save(User(
            phoneNumber = request.phoneNumber,
            password = passwordEncoder.encode(request.password),
            name = request.name
        ))

        return UserResponse.of(
            id = user.id,
            name = user.name,
            phoneNumber = user.phoneNumber
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

        val accessToken = jwtTokenProvider.createAccessToken(user.id)
        val refreshToken = jwtTokenProvider.createRefreshToken(user.id)
        refreshTokenRepository.save(refreshToken)

        return AuthResponse.of(
            userInfo = UserResponse.of(
                id = user.id,
                name = user.name,
                phoneNumber = user.phoneNumber
            ),
            tokenInfo = TokenInfo.of(
                accessToken = accessToken,
                refreshToken = refreshToken,
                accessTokenExpiresIn = jwtTokenProvider.getAccessTokenValidityInSeconds()
            )
        )
    }

    fun logout(userId: UUID) {
        refreshTokenRepository.deleteByUserId(userId)
    }

    fun reissue(tokenRequest: TokenRequest): TokenInfo {
        if (!jwtTokenProvider.validateToken(tokenRequest.refreshToken)) {
            throw InvalidRefreshTokenException(tokenRequest.refreshToken)
        }


        val userId = jwtTokenProvider.getUserId(tokenRequest.accessToken)
        val refreshToken = refreshTokenRepository.findByUserId(userId)
            ?: throw UserAlreadyLogoutException(userId)

        if (refreshToken.token != tokenRequest.refreshToken) {
            throw InvalidRefreshTokenException(tokenRequest.refreshToken)
        }

        val newAccessToken = jwtTokenProvider.createAccessToken(userId)
        val newRefreshToken = jwtTokenProvider.createRefreshToken(userId)

        return TokenInfo(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken,
            accessTokenExpiresIn = Date().time + jwtTokenProvider.getAccessTokenValidityInSeconds()
        )
    }
}