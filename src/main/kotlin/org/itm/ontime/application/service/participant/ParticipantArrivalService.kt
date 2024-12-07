package org.itm.ontime.application.service.participant

import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.application.exception.participant.arrival.AlreadyArrivedException
import org.itm.ontime.application.exception.participant.participant.NotParticipantException
import org.itm.ontime.application.service.user.UserService
import org.itm.ontime.domain.meeting.Meeting
import org.itm.ontime.domain.participant.Participant
import org.itm.ontime.domain.participant.ParticipantArrivalStatus
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.itm.ontime.infrastructure.repository.participant.ParticipantRepository
import org.itm.ontime.presentation.dto.request.participant.ParticipantArrivalResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class ParticipantArrivalService(
    private val participantRepository: ParticipantRepository,
    private val meetingRepository: MeetingRepository,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun getParticipantArrival(meetingId: UUID, participantId: UUID): ParticipantArrivalResponse {
        val (meeting, participant) = validateMeetingAndParticipant(meetingId, participantId)

        val arrival = participant.arrival

        return ParticipantArrivalResponse.of(meeting.id, participant.id, arrival)
    }

    @Transactional(readOnly = true)
    fun getParticipantArrivals(meetingId: UUID): List<ParticipantArrivalResponse> {
        val meeting = meetingRepository.findById(meetingId)
           .orElseThrow { MeetingNotFoundException(meetingId) }

        val participants = participantRepository.findAllByMeetingId(meetingId)

        val arrivalResponses = participants.map { participant ->
            ParticipantArrivalResponse.of(
                meetingId = meeting.id,
                participantId = participant.id,
                arrival = participant.arrival
               )
        }

        return arrivalResponses
    }

    @Transactional
    fun markArrival(
        meetingId: UUID,
        participantId: UUID,
        arrivalTime: LocalDateTime
    ): ParticipantArrivalResponse {
        val (meeting, participant) = validateMeetingAndParticipant(meetingId, participantId)

        val arrival = participant.arrival

        if (arrival.status == ParticipantArrivalStatus.ARRIVED) {
            throw AlreadyArrivedException(participant.id, meeting.id)
        }

        val isLate = isLate(arrivalTime, meeting.meetingDateTime)
        arrival.markArrival(arrivalTime, isLate)

        userService.updateUserStatistics(
            userId = participant.participant.id,
            isLate = isLate
        )

        return ParticipantArrivalResponse.of(meeting.id, participant.id, arrival)
    }

    private fun validateMeetingAndParticipant(
        meetingId: UUID,
        participantId: UUID
    ): Pair<Meeting, Participant> {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        val participant = participantRepository.findByMeetingIdAndParticipantId(meeting.id, participantId)
            ?: throw NotParticipantException(participantId, meeting.id)
        return Pair(meeting, participant)
    }

    fun isLate(arrivalTime: LocalDateTime, meetingDateTime: LocalDateTime): Boolean {
        return arrivalTime.isAfter(meetingDateTime) 
    }

}