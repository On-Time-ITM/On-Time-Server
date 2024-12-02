package org.itm.ontime.infrastructure.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.itm.ontime.application.auth.exception.common.ExpiredTokenException
import org.itm.ontime.application.auth.exception.common.InvalidTokenException
import org.itm.ontime.domain.auth.entity.RefreshToken
import org.itm.ontime.domain.auth.repository.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secretKey: String,

    @Value("\${jwt.access-token-validity-in-seconds}")
    private val accessTokenValidityInSeconds: Long,

    @Value("\${jwt.refresh-token-validity-in-seconds}")
    private val refreshTokenValidityInSeconds: Long,

    private val refreshTokenRepository: RefreshTokenRepository
) {
    private val key: SecretKey

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer "
    }

    init {
        val keyBytes = Base64.getDecoder().decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createAccessToken(userId: UUID): String {
        val authentication = UsernamePasswordAuthenticationToken(userId, "", listOf())
        val now = Date()
        val expiration = Date(now.time + accessTokenValidityInSeconds)

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authentication.authorities)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun createRefreshToken(userId: UUID): RefreshToken {
        val now = Date()
        val expiration = Date(now.time + refreshTokenValidityInSeconds)

        val token = Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()

        val refreshToken = refreshTokenRepository.save(RefreshToken(
            userId = userId,
            token = token,
            expiresAt = expiration
        ))

        return refreshToken
    }

    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {} // 잘못된 JWT 서명
                is MalformedJwtException -> {} // 잘못된 JWT 토큰
                is ExpiredJwtException -> {} // 만료된 JWT 토큰
                is UnsupportedJwtException -> {} // 지원되지 않는 JWT 토큰
                is IllegalArgumentException -> {} // JWT 토큰이 잘못됨
            }
            return false
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(7)
        } else null
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException(token)
        } catch (e: Exception) {
            throw InvalidTokenException(token)
        }
    }

    fun getAuthorities(token: String): List<GrantedAuthority> {
        val claims = getClaims(token)
        val roles = claims["roles"] as List<*>?
        return roles?.map { SimpleGrantedAuthority(it.toString()) } ?: emptyList()
    }

    fun getUserId(token: String): UUID {
        return UUID.fromString(getClaims(token).subject)
    }

    fun getAccessTokenValidityInSeconds(): Long = accessTokenValidityInSeconds
}
