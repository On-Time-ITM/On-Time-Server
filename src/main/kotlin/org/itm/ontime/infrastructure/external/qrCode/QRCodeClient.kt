package org.itm.ontime.infrastructure.external.qrCode


import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class QRCodeClient {
    fun createQRCode(data: String, size: String = "200x200"): String {
        val encodedData = URLEncoder.encode(data, StandardCharsets.UTF_8.toString())
        val urlString = "https://api.qrserver.com/v1/create-qr-code/?data=$encodedData&size=$size"

        val connection = (URI(urlString).toURL().openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 5000
            readTimeout = 5000
        }

        return connection.inputStream.use { it.readBytes().toString() }
    }
}