package ar.edu.unlam.mobile.scaffolding.domain.restaurants.models

import android.location.Location

data class Restaurant(
    val id: Int,
    val address: String,
    val location: Location,
    val calculateDistance: Float,
)
