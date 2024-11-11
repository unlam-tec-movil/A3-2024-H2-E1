package ar.edu.unlam.mobile.scaffolding.data.restaurants.repository

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantsLocalRepository {
    suspend fun saveRestaurants(restaurants: List<Restaurant>)

    fun getRestaurants(): Flow<List<Restaurant>>
}
