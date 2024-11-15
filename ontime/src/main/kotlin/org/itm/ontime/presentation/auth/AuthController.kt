package org.itm.ontime.presentation.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.auth.service.AuthService
import org.itm.ontime.domain.auth.exception.common.InvalidRefreshTokenException
import org.itm.ontime.domain.auth.exception.local.DuplicationPhoneNumberException
import org.itm.ontime.domain.auth.exception.local.InvalidPasswordException
import org.itm.ontime.presentation.auth.request.LoginRequest
import org.itm.ontime.presentation.auth.request.SignUpRequest
import org.itm.ontime.presentation.auth.request.TokenRefreshRequest
import org.itm.ontime.presentation.auth.response.TokenResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication", description = "Authentication API")
class AuthController(
    private val authService: AuthService
) {

    @Operation(summary = "Sign Up", description = "Register a new user with phone number")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Invalid input or duplicate phone number",
            content = [Content(schema = Schema(oneOf = [
                DuplicationPhoneNumberException::class
            ]))])
    ])
    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody request: SignUpRequest
    ): TokenResponse {
        return authService.signUp(request)
    }

    @Operation(summary = "Sign Up", description = "Login with phone number and password")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Invalid credentials",
            content = [Content(schema = Schema(implementation = InvalidPasswordException::class))])
    ])
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): TokenResponse {
        return authService.login(request)
    }

    @Operation(summary = "Refresh token", description = "Get new access token using refresh token")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "401", description = "Invalid refresh token",
            content = [Content(schema = Schema(implementation = InvalidRefreshTokenException::class))])
    ])
    @PostMapping("/refresh")
    fun refreshToken(
        @Valid @RequestBody request: TokenRefreshRequest
    ): TokenResponse {
        return authService.refreshToken(request)
    }

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal userId: UUID) {
        authService.logout(userId)
    }
}