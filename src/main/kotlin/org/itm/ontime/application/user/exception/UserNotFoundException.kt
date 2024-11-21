package org.itm.ontime.application.user.exception

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.global.error.BusinessException
import org.itm.ontime.global.error.CommonErrorCode
import java.util.UUID

@Schema(description = "Exception thrown when a user cannot be found")
class UserNotFoundException private constructor(
    errorMessage: String
) : BusinessException(
    CommonErrorCode.USER_NOT_FOUND,
    errorMessage
) {
    companion object {
        @JvmStatic
        fun fromId(
            @Schema(description = "ID of the user that cannot be found", example = "123")
            userId: UUID
        ) = UserNotFoundException("User not found with id: $userId")

        @JvmStatic
        fun fromPhoneNumber(
            @Schema(description = "Phone number that cannot be found", example = "010-1234-5678")
            phoneNumber: String
        ) = UserNotFoundException("User not found with phone number: $phoneNumber")
    }
}
