package ar.edu.unlam.mobile.scaffolding.domain.restaurants.models

data class Restaurant(
    val id: Int,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val calculateDistance: Float,
)
