package org.itm.ontime.global.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.itm.ontime.domain.PrimaryKeyEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : PrimaryKeyEntity() {

    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var updatedAt: LocalDateTime ?= null

}