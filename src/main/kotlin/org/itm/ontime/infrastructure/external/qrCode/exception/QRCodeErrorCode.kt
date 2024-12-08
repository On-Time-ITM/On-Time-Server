package org.itm.ontime.infrastructure.external.qrCode.exception

import org.itm.ontime.application.exception.common.ErrorCode
import org.springframework.http.HttpStatus

enum class QRCodeErrorCode(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : ErrorCode {

    QR_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "Q001", "QR code not found"),
    QR_CODE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Q002", "QR code already exists"),

}