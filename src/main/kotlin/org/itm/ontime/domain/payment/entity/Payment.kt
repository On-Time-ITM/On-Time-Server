package org.itm.ontime.domain.payment.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.itm.ontime.global.entity.BaseEntity

@Entity
@Table(name = "payment")
class Payment(
    val amount: Int,
    val account: AccountInfo

) : BaseEntity()