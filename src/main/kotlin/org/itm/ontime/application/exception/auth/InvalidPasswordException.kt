package org.itm.ontime.application.exception.auth

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException

@Schema(description = "Exceptions that occur when the password is invalid")
class InvalidPasswordException() : BusinessException(AuthErrorCode.INVALID_PASSWORD)