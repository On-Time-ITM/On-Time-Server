package org.itm.ontime.application.user.service

import org.itm.ontime.application.meeting.exception.MeetingNotFoundException
import org.itm.ontime.application.user.exception.UserLocationNotFoundException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.domain.user.repository.UserLocationRepository
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.user.request.UpdateUserLocationRequest
import org.itm.ontime.presentation.user.response.UserLocationResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserLocationService (
    private val userLocationRepository: UserLocationRepository,
    private val userRepository: UserRepository,
    private val meetingRepository: MeetingRepository
){
    fun getUserLocation() : UserLocationResponse{
        val user = userRepository.findById(request.userId)
            .orElseThrow { UserNotFoundException.fromId(request.userId) }
        
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow{ MeetingNotFoundException(request.meetingId) }
        
        val userLocation = userLocationRepository.findByUserIdAndMeetingId(user.id, meeting.id)
            .orElseThrow { UserLocationNotFoundException(user.id, meeting.id) }
        
        return UserLocationResponse.of(
            userLocation.user.id, 
            userLocation.location
        )
    }
    fun updateUserLocation(request: UpdateUserLocationRequest) {
        
    }
}