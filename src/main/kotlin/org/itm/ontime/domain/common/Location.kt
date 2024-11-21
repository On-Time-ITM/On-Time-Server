package org.itm.ontime.domain.common

import jakarta.persistence.Embeddable

@Embeddable
data class Location(
    val latitude: Double,
    val longitude: Double,
    val address: String
)
