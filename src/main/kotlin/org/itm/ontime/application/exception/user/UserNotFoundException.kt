package org.itm.ontime.application.exception.user

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when a user cannot be found")
class UserNotFoundException (
    errorMessage: String
) : BusinessException(
    UserErrorCode.USER_NOT_FOUND,
    errorMessage
) {
    companion object {
        @JvmStatic
        fun fromId(
            @Schema(description = "ID of the user that cannot be found", example = "123")
            userId: UUID
        ) = UserNotFoundException("User not found with id: $userId")

        @JvmStatic
        fun fromIds(
            @Schema(description = "List of user IDs that were not found",
                example = "['550e8400-e29b-41d4-a716-446655440000', '7c9e6679-7425-40de-944b-e07fc1f90ae7']")
            userIds: List<UUID>
        ) = UserNotFoundException("User not found with ids: $userIds")

        @JvmStatic
        fun fromPhoneNumber(
            @Schema(description = "Phone number that cannot be found", example = "010-1234-5678")
            phoneNumber: String
        ) = UserNotFoundException("User not found with phone number: $phoneNumber")
    }
}
