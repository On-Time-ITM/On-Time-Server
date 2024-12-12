package org.itm.ontime.application.service.participant

import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.application.exception.participant.participant.NotParticipantException
import org.itm.ontime.application.exception.participant.participant.ParticipantNotExistsException
import org.itm.ontime.domain.common.Location
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.itm.ontime.infrastructure.repository.participant.ParticipantRepository
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.itm.ontime.presentation.dto.request.participant.ParticipantLocationsRequest
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationInfo
import org.itm.ontime.presentation.dto.response.participant.ParticipantLocationsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ParticipantLocationService (
    private val participantRepository: ParticipantRepository,
    private val meetingRepository: MeetingRepository, private val userRepository: UserRepository
){

    @Transactional(readOnly = true)
    fun getParticipantLocation(meetingId: UUID, participantId: UUID) : ParticipantLocationInfo {
        val meeting = meetingRepository.findById(meetingId)
           .orElseThrow { MeetingNotFoundException(meetingId) }

        val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantId)
           ?: throw ParticipantNotExistsException(meeting.id)

        return ParticipantLocationInfo.of(participant)
    }

    @Transactional(readOnly = true)
    fun getParticipantLocations(meetingId: UUID) : ParticipantLocationsResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        val participants = participantRepository.findAllByMeetingId(meetingId)

        return ParticipantLocationsResponse.of(
            meetingId = meeting.id,
            participantLocationInfos = participants.map { ParticipantLocationInfo.of(it) }
        )
    }

    @Transactional
    fun updateParticipantLocations(meetingId: UUID, request: ParticipantLocationsRequest): ParticipantLocationsResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        request.participantLocationInfos.map { participantLocationInfo ->
            val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantLocationInfo.participantId)
                ?: throw NotParticipantException(participantLocationInfo.participantId, meeting.id)

            participantLocationInfo.participantLocation?.let { participant.updateLocation(it) }
        }

        return ParticipantLocationsResponse.of(
            meetingId = meeting.id,
            participantLocationInfos = request.participantLocationInfos
        )
    }


    @Transactional
    fun updateParticipantLocation(
        meetingId: UUID,
        participantId: UUID,
        participantLocation: Location
    ): ParticipantLocationInfo {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }
        val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantId)
            ?: throw NotParticipantException(participantId, meeting.id)

        participant.updateLocation(participantLocation)

        return ParticipantLocationInfo.of(participant)
    }
}