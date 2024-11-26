package org.itm.ontime.domain.meeting.repository

import org.itm.ontime.domain.meeting.entity.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileImageRepository : JpaRepository<ProfileImage, UUID>  {
}