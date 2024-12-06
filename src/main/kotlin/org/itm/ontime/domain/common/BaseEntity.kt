package org.itm.ontime.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : PrimaryKeyEntity() {

    @CreatedDate
    @Column
    val createdDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column
    var updatedAt: LocalDateTime ?= null

}