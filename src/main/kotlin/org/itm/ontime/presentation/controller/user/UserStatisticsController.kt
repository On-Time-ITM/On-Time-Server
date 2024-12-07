package org.itm.ontime.presentation.controller.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.itm.ontime.application.service.user.UserService
import org.itm.ontime.presentation.dto.response.user.UserStatisticsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/v1/user/{userId}/statistics")
@Tag(name = "User Statistics", description = "User statistics API")
class UserStatisticsController(
    private val userService: UserService
) {
    @Operation(
        summary = "Get user statistics",
        description = "Get user statistics for a given user ID"
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "User statistics found successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "User not found"
        )
    )
    @GetMapping
    fun getUserStatistics(@PathVariable userId: UUID): ResponseEntity<UserStatisticsResponse> {
        val response = userService.getUserStatistics(userId)
        return ResponseEntity.ok(response)
    }
}