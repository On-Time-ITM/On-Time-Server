package org.itm.ontime.domain.location.entity

import jakarta.persistence.Embeddable

@Embeddable
data class Location(
    val latitude: Double,
    val longitude: Double,
    val address: String
)
