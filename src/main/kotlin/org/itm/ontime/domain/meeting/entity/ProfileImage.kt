package org.itm.ontime.domain.meeting.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.itm.ontime.global.entity.BaseEntity

@Entity
class ProfileImage(
    @Column(nullable = false)
    val prompt: String,

    @Column(nullable = false)
    val model: String = "dall-e-3",

    @Column(nullable = false)
    val size: String,

    @Column(nullable = false)
    val quality: String = "standard",

    @Column(nullable = false)
    val n: Int = 1,

    @Column(nullable = false)
    val url: String
) : BaseEntity(){
}