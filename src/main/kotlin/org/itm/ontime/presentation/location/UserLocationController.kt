package org.itm.ontime.presentation.location

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.location.service.UserLocationService
import org.itm.ontime.presentation.location.request.CreateUserLocationsRequest
import org.itm.ontime.presentation.location.request.UpdateUserLocationsRequest
import org.itm.ontime.presentation.location.response.UserLocationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/location")
@Tag(name = "User Location", description = "User location management APIs")
class UserLocationController (
    private val userLocationService: UserLocationService
){
    @Schema(description = "Get user location list")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "User location list"
        ),
        ApiResponse(
            responseCode = "404",
            description = "No user location found for the meeting"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid meeting ID"
        )
    ])
    @GetMapping(value = ["/{meetingId}"])
    fun getUserLocationList(@PathVariable("meetingId") meetingId: UUID) : ResponseEntity<List<UserLocationResponse>> {
        val response = userLocationService.getUserLocationList(meetingId)
        return ResponseEntity.ok(response)
    }


    @Schema(description = "Create user locations")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "User locations created successfully"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input"
        )
    ])
    @PostMapping
    fun createUserLocations(@RequestBody @Valid request: CreateUserLocationsRequest) : ResponseEntity<List<UUID>> {
        val response = userLocationService.createUserLocations(request)
        return ResponseEntity.ok(response)
    }


    @Schema(description = "Update user locations")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "User locations updated successfully"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input"
        )
    ])
    @PatchMapping
    fun updateUserLocations(@RequestBody @Valid request: UpdateUserLocationsRequest) : ResponseEntity<List<UUID>> {
        val response = userLocationService.updateUserLocations(request)
        return ResponseEntity.ok(response)
    }
}