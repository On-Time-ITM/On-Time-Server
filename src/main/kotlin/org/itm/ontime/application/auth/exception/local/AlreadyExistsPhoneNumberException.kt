package org.itm.ontime.application.auth.exception.local

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.auth.exception.common.AuthErrorCode
import org.itm.ontime.global.error.BusinessException


@Schema(description = "Exceptions that occur when the phone number is duplicated")
class AlreadyExistsPhoneNumberException (
    @Schema(description = "Phone number that caused the duplication", example = "010-1234-5678")
    val phoneNumber: String
) : BusinessException(
    AuthErrorCode.DUPLICATE_PHONE_NUMBER,
    "Phone number already exists: $phoneNumber"
)