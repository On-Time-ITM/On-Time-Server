package org.itm.ontime.presentation.meeting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.itm.ontime.application.meeting.service.QRCodeService
import org.itm.ontime.presentation.meeting.request.QRCodeRequest
import org.itm.ontime.presentation.meeting.response.qrCode.QRCodeResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/qr")
@Tag(name = "QR Code", description = "QR Code management APIs")
class QRCodeController(
    private val qrCodeService: QRCodeService
) {
    @Operation(
        summary = "Create QR code",
        description = "Create a QR code for a meeting"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "QR code created successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Meeting not found"
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid meeting ID"
        )
    ])
    @PostMapping
    fun createQRCode(@RequestBody @Valid request: QRCodeRequest) : ResponseEntity<QRCodeResponse>{
        val response = qrCodeService.createQRCode(request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "Get QR code",
        description = "Get a QR code for a meeting"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "QR code found"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Meeting not found"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid meeting ID"
        )
    ])
    @GetMapping("/{meetingId}")
    fun getQRCode(@PathVariable meetingId: UUID) : ResponseEntity<QRCodeResponse>{
        val response = qrCodeService.getQRCode(meetingId)
        return ResponseEntity.ok(response)
    }
}