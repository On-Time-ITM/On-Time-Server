package org.itm.ontime.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.itm.ontime.domain.auth.exception.common.ExpiredTokenException
import org.itm.ontime.domain.auth.exception.common.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import io.jsonwebtoken.security.Keys;
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
    private val refreshTokenValidityInSeconds: Long


) {
    private val key: SecretKey

    init {
        val keyBytes = Base64.getDecoder().decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createAccessToken(userId: UUID): String {
        val now = Date()
        val validity = Date(now.time + accessTokenValidityInSeconds * 1000)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key)
            .compact()
    }

    fun createRefreshToken(userId: UUID): String {
        val now = Date()
        val validity = Date(now.time + refreshTokenValidityInSeconds * 1000)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = getClaims(token)
            return !claims.expiration.before(Date())
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException(token)
        } catch (e: Exception) {
            throw InvalidTokenException(token)
        }
    }

    private fun getClaims(token: String): Claims { // TODO : 왜 얘만 private로 해야 할까
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

    fun getUserId(token: String): Long {
        return getClaims(token).subject.toLong()
    }

    fun getAccessTokenValidityInSeconds(): Long = accessTokenValidityInSeconds
}

// CustomUserDetails.kt
class CustomUserDetails(
    private val userId: Long
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String? = null

    override fun getUsername(): String = userId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}