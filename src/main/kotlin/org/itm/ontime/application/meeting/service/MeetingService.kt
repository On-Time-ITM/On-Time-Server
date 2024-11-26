package org.itm.ontime.application.meeting.service


import org.itm.ontime.application.meeting.exception.MeetingNotFoundException
import org.itm.ontime.application.meeting.exception.NotMeetingHostException
import org.itm.ontime.application.user.exception.UserNotFoundException
import org.itm.ontime.domain.meeting.entity.Meeting
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.domain.user.repository.UserRepository
import org.itm.ontime.presentation.meeting.request.CreateMeetingRequest
import org.itm.ontime.presentation.meeting.request.DeleteMeetingRequest
import org.itm.ontime.presentation.meeting.response.MeetingResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MeetingService(
    private val userRepository: UserRepository,
    private val meetingRepository: MeetingRepository,
    private val profileImageService: ProfileImageService
) {
    @Transactional(readOnly = true)
    fun getMeeting(meetingId: UUID) : MeetingResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow { MeetingNotFoundException(meetingId) }

        return MeetingResponse.of(meeting)
    }

    @Transactional(readOnly = true)
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

        val profileImage = profileImageService.createProfileImage(request)

        val meeting = Meeting(
            name = request.name,
            meetingDateTime = request.meetingDateTime,
            location = request.location,
            lateFee = request.lateFee,
            accountInfo = request.accountInfo,
            host = host,
            participants = users,
            profileImage = profileImage
        )
        meetingRepository.save(meeting)
        
        return meeting.id
    }



    @Transactional
    fun deleteMeeting(request: DeleteMeetingRequest): UUID {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow { MeetingNotFoundException(request.meetingId) }

        val user = userRepository.findById(request.hostId)
            .orElseThrow { UserNotFoundException.fromId(request.hostId) }

        if (meeting.host.id != user.id) {
            throw NotMeetingHostException(user.id, meeting.id)
        }

        meetingRepository.deleteById(meeting.id)
        return meeting.id
    }
}