package org.itm.ontime.domain.payment.entity

import jakarta.persistence.Embeddable

@Embeddable
data class AccountInfo(
    val bankName: String,
    val accountNumber: String,
)
