package org.itm.ontime.presentation.meeting.response

import java.util.*

data class QRCodeResponse(
    val meetingId: UUID,
    val qrCode: String
) {
    companion object {
        fun of(meetingId: UUID, qrCode: String) = QRCodeResponse(meetingId, qrCode)
    }
}
