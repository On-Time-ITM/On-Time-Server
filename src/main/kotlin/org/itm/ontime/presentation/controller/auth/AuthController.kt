package org.itm.ontime.presentation.controller.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.exception.auth.AlreadyExistsPhoneNumberException
import org.itm.ontime.application.exception.auth.InvalidPasswordException
import org.itm.ontime.application.exception.auth.InvalidRefreshTokenException
import org.itm.ontime.application.service.auth.AuthService
import org.itm.ontime.presentation.dto.request.auth.LoginRequest
import org.itm.ontime.presentation.dto.request.auth.SignUpRequest
import org.itm.ontime.presentation.dto.request.auth.TokenRequest
import org.itm.ontime.presentation.dto.response.auth.AuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication", description = "Authentication API")
class AuthController(
    private val authService: AuthService
) {

    @Operation(
        summary = "Sign Up",
        description = "Register a new user with phone number"
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Invalid input or duplicate phone number",
            content = [Content(schema = Schema(oneOf = [
                AlreadyExistsPhoneNumberException::class
            ]))])
    ])
    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody request: SignUpRequest
    ): ResponseEntity<AuthResponse> {
        val response = authService.signUp(request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Sign Up",
        description = "Login with phone number and password"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "400", description = "Invalid credentials",
                content = [Content(schema = Schema(implementation = InvalidPasswordException::class))])
    ])
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<AuthResponse> {
        val response = authService.login(request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Refresh token",
        description = "Get new access token using refresh token"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "401", description = "Invalid refresh token",
                content = [Content(schema = Schema(implementation = InvalidRefreshTokenException::class))])
    ])
    @PostMapping("/reissue")
    fun reissueToken(
        @Valid @RequestBody request: TokenRequest
    ): ResponseEntity<AuthResponse> {
        val response = authService.reissue(request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Logout",
        description = "Logout the currently authenticated user and invalidate the access token."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Logout successful"),
            ApiResponse(responseCode = "401", description = "Unauthorized, invalid or expired access token")
        ]
    )
    @PostMapping("/logout")
    fun logout(@RequestParam userId: UUID): ResponseEntity<Unit> {
        authService.logout(userId)
        return ResponseEntity.ok().build()
    }
}