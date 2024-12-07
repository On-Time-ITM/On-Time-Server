package org.itm.ontime.application.service.meeting


import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.application.exception.meeting.NonFriendInviteException
import org.itm.ontime.application.exception.meeting.NotMeetingHostException
import org.itm.ontime.application.exception.user.UserNotFoundException
import org.itm.ontime.domain.friendship.FriendshipStatus
import org.itm.ontime.domain.meeting.Meeting
import org.itm.ontime.domain.participant.Participant
import org.itm.ontime.domain.user.User
import org.itm.ontime.infrastructure.repository.friendship.FriendshipRepository
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
import org.itm.ontime.infrastructure.repository.participant.ParticipantRepository
import org.itm.ontime.infrastructure.repository.user.UserRepository
import org.itm.ontime.presentation.dto.request.meeting.CreateMeetingRequest
import org.itm.ontime.presentation.dto.request.meeting.DeleteMeetingRequest
import org.itm.ontime.presentation.dto.response.meeting.MeetingResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MeetingService(
    private val meetingRepository: MeetingRepository,
    private val participantRepository: ParticipantRepository,
    private val userRepository: UserRepository,
    private val friendshipRepository: FriendshipRepository

    ) {
    @Transactional(readOnly = true)
    fun getMeeting(meetingId: UUID) : MeetingResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        return MeetingResponse.of(meeting)
    }

    @Transactional(readOnly = true)
    fun getMeetings(userId: UUID) : List<MeetingResponse> {
        val user = userRepository.findById(userId)
            .orElseThrow{ UserNotFoundException.fromId(userId) }

        return MeetingResponse.of(
            meetingRepository.findAllByParticipantsParticipantId(user.id)
        )
    }

    @Transactional
    fun createMeeting(request: CreateMeetingRequest) : UUID {
        val host = userRepository.findById(request.hostId)
            .orElseThrow{ UserNotFoundException.fromId(request.hostId) }

        val participants = userRepository.findAllById(request.participantIds)
        validateParticipants(request.participantIds, participants)
        validateFriendships(host, participants)
        participants.add(host)

        val meeting = Meeting(
            name = request.name,
            meetingDateTime = request.meetingDateTime,
            location = request.location,
            lateFee = request.lateFee,
            accountInfo = request.accountInfo,
            host = host,
            profileImage = request.profileImage
        )
        meetingRepository.save(meeting)

        participants.forEach { participant ->
            Participant(
                meeting = meeting,
                participant = participant
            ).also {
                participantRepository.save(it)
                meeting.participants.add(it)
            }
        }

        return meeting.id
    }

    private fun validateParticipants(
        requestIds: List<UUID>,
        foundParticipants: List<User>
    ) {
        if (foundParticipants.size != requestIds.size) {
            val foundIds = foundParticipants.map { it.id }
            val notFoundIds = requestIds - foundIds.toSet()
            throw UserNotFoundException.fromIds(notFoundIds)
        }
    }

    private fun validateFriendships(host: User, participants: List<User>) {
        val nonFriends = participants.filter { participant ->
            !friendshipRepository.existsByRequesterAndReceiverAndStatus(
                host, participant, FriendshipStatus.ACCEPTED
            ) && !friendshipRepository.existsByRequesterAndReceiverAndStatus(
                participant, host, FriendshipStatus.ACCEPTED
            )
        }

        if (nonFriends.isNotEmpty()) {
            throw NonFriendInviteException(host.id, nonFriends.map { it.id })
        }
    }

    @Transactional
    fun deleteMeeting(request: DeleteMeetingRequest): UUID {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        val host = userRepository.findById(request.hostId)
            .orElseThrow { UserNotFoundException.fromId(request.hostId) }

        if (meeting.host.id != host.id) {
            throw NotMeetingHostException(host.id, meeting.id)
        }

        meetingRepository.deleteById(meeting.id)
        return meeting.id
    }

}