package org.itm.ontime.global.error

abstract class BusinessException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.message
) : RuntimeException(message)
