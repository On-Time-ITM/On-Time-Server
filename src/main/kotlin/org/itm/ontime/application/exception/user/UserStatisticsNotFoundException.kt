package org.itm.ontime.application.exception.user

import io.swagger.v3.oas.annotations.media.Schema
import org.itm.ontime.application.exception.common.BusinessException
import java.util.*

@Schema(description = "Exception thrown when a user cannot be found")
class UserStatisticsNotFoundException (
    private val
    userId: UUID,
) : BusinessException(
    UserErrorCode.USER_STATISTICS_NOT_FOUND,
    "User statistics not found for user: $userId"
)
