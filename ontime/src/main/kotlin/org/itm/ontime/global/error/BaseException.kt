package org.itm.ontime.global.error

abstract class BaseException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.message
) : RuntimeException(message)