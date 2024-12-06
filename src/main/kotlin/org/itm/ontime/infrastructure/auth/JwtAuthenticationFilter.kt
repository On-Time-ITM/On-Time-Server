package org.itm.ontime.infrastructure.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            resolveToken(request)?.let {
                if (jwtTokenProvider.validateToken(it)) {
                    val userId = jwtTokenProvider.getUserId(it)
                    val authorities = jwtTokenProvider.getAuthorities(it)
                    val userDetails = CustomUserDetails(userId, authorities)
                    val authentication = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")?.let { bearerToken ->
            if (bearerToken.startsWith("Bearer ", ignoreCase = true)) {
                bearerToken.substring(7)
            } else null
        }
    }
}