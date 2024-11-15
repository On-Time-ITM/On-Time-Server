package org.itm.ontime.domain.auth.exception.local

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode

@Schema(description = "Exceptions that occur when the password is invalid")
class InvalidPasswordException() : BusinessException(ErrorCode.INVALID_PASSWORD)