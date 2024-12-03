package org.itm.ontime.domain.meeting.entity.profileImage

import com.aallam.openai.api.image.ImageSize
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "profile_image")
class ProfileImage(
    @Column(nullable = false)
    val prompt: String,

    @Column(nullable = false)
    val model: String = "dall-e-3",

    @Column(nullable = false)
    val size: ImageSize? = ImageSize.is512x512,

    @Column(nullable = false)
    val quality: String = "standard",

    @Column(nullable = false)
    val url: String
) : BaseEntity(){
}