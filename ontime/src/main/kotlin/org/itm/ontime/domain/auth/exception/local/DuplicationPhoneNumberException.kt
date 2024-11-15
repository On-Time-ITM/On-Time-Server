package org.itm.ontime.domain.auth.exception.local

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.ErrorCode


class DuplicationPhoneNumberException (
    @Schema(description = "Phone number that caused the duplication", example = "010-1234-5678")
    val phoneNumber: String
) : BusinessException(
    ErrorCode.DUPLICATE_PHONE_NUMBER,
    "Phone number already exists: $phoneNumber"
)