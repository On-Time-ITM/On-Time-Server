package org.itm.ontime.domain.common

import jakarta.persistence.Embeddable

@Embeddable
data class AccountInfo(
    val bankName: String,
    val accountNumber: String,
    val accountHost: String,
)
