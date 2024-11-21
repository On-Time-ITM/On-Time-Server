package org.itm.ontime.application.meeting.service

import jakarta.transaction.Transactional
import org.itm.ontime.application.meeting.exception.MeetingNotFoundException
import org.itm.ontime.application.meeting.exception.NotMeetingHostException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.meeting.entity.Meeting
import org.itm.ontime.domain.meeting.entity.MeetingParticipant
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.meeting.request.CreateMeetingRequest
import org.itm.ontime.presentation.meeting.request.DeleteMeetingRequest
import org.itm.ontime.presentation.meeting.response.MeetingResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MeetingService(
    private val userRepository: UserRepository,
    private val meetingRepository: MeetingRepository
) {
    fun getMeeting(meetingId: UUID) : MeetingResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        return MeetingResponse.of(meeting)
    }

    fun getMeetingList(userId: UUID) : List<MeetingResponse> {
        val user = userRepository.findById(userId)
            .orElseThrow{ UserNotFoundException.fromId(userId) }

        return MeetingResponse.of(
            meetingRepository.findAllByParticipantsUserId(user.id)
        )
    }

    @Transactional
    fun createMeeting(request: CreateMeetingRequest) : UUID {
        val host = userRepository.findById(request.hostId)
            .orElseThrow{ UserNotFoundException.fromId(request.hostId) }

        val users = userRepository.findAllById(request.participantIds)
        if (users.size != request.participantIds.size) {
            val foundIds = users.map { it.id }
            val notFoundIds = request.participantIds - foundIds.toSet()
            throw UserNotFoundException.fromIds(notFoundIds)
        }

        val meeting = Meeting(
            name = request.name,
            meetingDateTime = request.meetingDateTime,
            location = request.location,
            lateFee = request.lateFee,
            bankAccount = request.bankAccount,
            host = host
        )

        val participants = users.map { user ->
            MeetingParticipant(meeting = meeting, user = user)
        }

        meeting.apply { this.participants.addAll(participants) }
        return meeting.id
    }

    fun deleteMeeting(request: DeleteMeetingRequest): UUID {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        val user = userRepository.findById(request.hostId)
            .orElseThrow{ UserNotFoundException.fromId(request.hostId) }

        if (meeting.host.id != user.id) {
            throw NotMeetingHostException(user.id, meeting.id)
        }

        meetingRepository.deleteById(request.meetingId)
        return meeting.id
    }
}