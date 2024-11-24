package org.itm.ontime.application.location.service

import org.itm.ontime.application.location.exception.UserLocationNotFoundException
import org.itm.ontime.application.meeting.exception.MeetingNotFoundException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.location.entity.UserLocation
import org.itm.ontime.domain.location.repository.UserLocationRepository
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.location.request.CreateUserLocationsRequest
import org.itm.ontime.presentation.location.request.UpdateUserLocationsRequest
import org.itm.ontime.presentation.location.response.UserLocationResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserLocationService (
    private val userLocationRepository: UserLocationRepository,
    private val userRepository: UserRepository,
    private val meetingRepository: MeetingRepository
){

    fun getUserLocationList(meetingId: UUID) : List<UserLocationResponse> {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        val userLocationList = userLocationRepository.findByMeetingId(meeting.id)

        return userLocationList.map {
                UserLocationResponse.of(
                    it.id,
                    it.user.id,
                    it.meeting.id,
                    it.location
                )
        }
    }


    fun createUserLocations(request: CreateUserLocationsRequest) : List<UUID> {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        val userLocationList = request.locations.map {
            val user = userRepository.findById(it.keys.first())
                .orElseThrow { UserNotFoundException.fromId(it.keys.first()) }
            UserLocation(
                user = user,
                meeting = meeting,
                location = it.values.first()
            )
        }
        userLocationRepository.saveAll(userLocationList)
        return userLocationList.map { it.id }.toList()
    }

    fun updateUserLocations(request: UpdateUserLocationsRequest) : List<UUID> {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        val userLocationIds : MutableList<UUID> = mutableListOf()

        request.locations.map {
            val user = userRepository.findById(it.keys.first())
                .orElseThrow { UserNotFoundException.fromId(it.keys.first()) }
            val userLocation = userLocationRepository.findByUserIdAndMeetingId(user.id, meeting.id)
                ?: throw UserLocationNotFoundException(user.id, meeting.id)
            userLocation.updateLocation(it.values.first())
            userLocationRepository.save(userLocation)
            userLocationIds.add(userLocation.id)
        }

        return userLocationIds
    }
}