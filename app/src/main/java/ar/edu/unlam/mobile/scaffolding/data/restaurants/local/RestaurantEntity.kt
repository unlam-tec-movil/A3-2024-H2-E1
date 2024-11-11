package ar.edu.unlam.mobile.scaffolding.data.restaurants.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey
    val id: Int,
    val address: String,
    val latitude: Double,
    val longitude: Double,
)

fun RestaurantEntity.asModel() =
    Restaurant(
        id = id,
        address = address,
        latitude = latitude,
        longitude,
        calculateDistance = 0f,
    )

fun Restaurant.asEntity() =
    RestaurantEntity(
        id = id,
        address = address,
        latitude = latitude,
        longitude = longitude,
    )
