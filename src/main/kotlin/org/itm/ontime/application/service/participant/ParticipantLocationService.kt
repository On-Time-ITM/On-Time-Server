package org.itm.ontime.application.service.participant

import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.application.exception.participant.participant.NotParticipantException
import org.itm.ontime.application.exception.participant.participant.ParticipantNotExistsException
import org.itm.ontime.application.exception.participant.location.ParticipantLocationNotFoundException
import org.itm.ontime.domain.participant.ParticipantLocation
import org.itm.ontime.infrastructure.repository.participant.ParticipantLocationRepository
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.itm.ontime.infrastructure.repository.participant.ParticipantRepository
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationInfo
import org.itm.ontime.presentation.dto.request.participant.ParticipantLocationsRequest
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ParticipantLocationService (
    private val participantLocationRepository: ParticipantLocationRepository,
    private val participantRepository: ParticipantRepository,
    private val meetingRepository: MeetingRepository
){

    @Transactional(readOnly = true)
    fun getParticipantLocation(meetingId: UUID, participantId: UUID) : ParticipantLocationInfo {
        val meeting = meetingRepository.findById(meetingId)
           .orElseThrow { MeetingNotFoundException(meetingId) }

        val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantId)
           ?: throw ParticipantNotExistsException(meeting.id)

        val participantLocation = participantLocationRepository.findByParticipantId(participant.id)
           ?: throw ParticipantLocationNotFoundException(participant.id, meeting.id)

        return ParticipantLocationInfo.of(participantLocation)
    }

    @Transactional(readOnly = true)
    fun getParticipantLocations(meetingId: UUID) : ParticipantLocationsResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        val participantLocations = participantLocationRepository.findByMeetingId(meeting.id)

        return ParticipantLocationsResponse.of(
            meetingId = meeting.id,
            participantLocationInfos = participantLocations.map { ParticipantLocationInfo.of(it) }
        )
    }

    @Transactional
    fun createUserLocations(request: ParticipantLocationsRequest) : ParticipantLocationsResponse {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        request.participantLocationInfos.map { participantLocationInfo ->
            val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantLocationInfo.participantId)
                ?: throw NotParticipantException(participantLocationInfo.participantId, meeting.id)

            val participantLocation = ParticipantLocation(
                participant = participant,
                location = participantLocationInfo.participantLocation
            )

            participantLocationRepository.save(participantLocation)
        }

        return ParticipantLocationsResponse.of(
            meetingId = meeting.id,
            participantLocationInfos = request.participantLocationInfos
        )
    }

    @Transactional
    fun updateUserLocations(request: ParticipantLocationsRequest): ParticipantLocationsResponse {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        request.participantLocationInfos.map { participantLocationInfo ->
            val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantLocationInfo.participantId)
                ?: throw NotParticipantException(participantLocationInfo.participantId, meeting.id)

            val participantLocation = participantLocationRepository.findByParticipantId(participant.id)
                ?: throw ParticipantLocationNotFoundException(participant.id, meeting.id)

            participantLocation.updateLocation(participantLocationInfo.participantLocation)
        }

        return ParticipantLocationsResponse.of(
            meetingId = meeting.id,
            participantLocationInfos = request.participantLocationInfos
        )
    }
}