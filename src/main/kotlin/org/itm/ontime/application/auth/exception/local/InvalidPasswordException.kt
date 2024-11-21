package org.itm.ontime.application.auth.exception.local

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.auth.exception.common.AuthErrorCode
import org.itm.ontime.global.error.BusinessException

@Schema(description = "Exceptions that occur when the password is invalid")
class InvalidPasswordException() : BusinessException(AuthErrorCode.INVALID_PASSWORD)