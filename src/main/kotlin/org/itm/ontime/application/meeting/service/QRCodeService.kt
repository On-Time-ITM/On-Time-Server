package org.itm.ontime.application.meeting.service

import org.itm.ontime.application.meeting.exception.MeetingNotFoundException
import org.itm.ontime.application.meeting.exception.QRCodeAlreadyExistsException
import org.itm.ontime.application.meeting.exception.QRCodeNotFoundException
import org.itm.ontime.domain.meeting.repository.MeetingRepository
import org.itm.ontime.infrastructure.external.qrCode.QRCodeClient
import org.itm.ontime.presentation.meeting.request.QRCodeRequest
import org.itm.ontime.presentation.meeting.response.QRCodeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QRCodeService(
    private val qrCodeClient: QRCodeClient,
    private val meetingRepository: MeetingRepository,
){
    @Transactional
    fun createQRCode(request: QRCodeRequest) : QRCodeResponse {
        val meeting = meetingRepository.findById(request.meetingId)
            .orElseThrow{ throw MeetingNotFoundException(request.meetingId) }

        if (meeting.hasQRCode()){
            throw QRCodeAlreadyExistsException(request.meetingId)
        }

        val qrCode = qrCodeClient.createQRCode(request.meetingName)
        meeting.createQRCode(qrCode)

        return QRCodeResponse.of(meeting.id, qrCode)
    }

    @Transactional(readOnly = true)
    fun getQRCode(meetingId: UUID) : QRCodeResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow{ throw MeetingNotFoundException(meetingId) }
        val qrCode = meeting.qrCode ?: throw QRCodeNotFoundException(meetingId)

        return QRCodeResponse.of(meeting.id, qrCode)
    }
}