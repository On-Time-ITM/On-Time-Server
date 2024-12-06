package org.itm.ontime.infrastructure.external.qrCode.dto

import java.util.*

data class QRCodeResponse(
    val meetingId: UUID,
    val qrCode: String
) {
    companion object {
        fun of(meetingId: UUID, qrCode: String) = QRCodeResponse(meetingId, qrCode)
    }
}
