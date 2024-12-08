package org.itm.ontime.infrastructure.external.qrCode

import org.itm.ontime.application.exception.meeting.MeetingNotFoundException
import org.itm.ontime.infrastructure.external.qrCode.dto.QRCodeRequest
import org.itm.ontime.infrastructure.external.qrCode.dto.QRCodeResponse
import org.itm.ontime.infrastructure.external.qrCode.exception.FCMTokenInvalidException
import org.itm.ontime.infrastructure.repository.meeting.MeetingRepository
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
        meeting.addQRCode(qrCode)

        return QRCodeResponse.of(meeting.id, qrCode)
    }

    @Transactional(readOnly = true)
    fun getQRCode(meetingId: UUID) : QRCodeResponse {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow{ throw MeetingNotFoundException(meetingId) }
        val qrCode = meeting.qrCode ?: throw FCMTokenInvalidException(meetingId)

        return QRCodeResponse.of(meeting.id, qrCode)
    }
}