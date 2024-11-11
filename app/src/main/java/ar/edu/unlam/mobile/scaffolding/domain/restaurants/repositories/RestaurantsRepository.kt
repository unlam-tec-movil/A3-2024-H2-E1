package ar.edu.unlam.mobile.scaffolding.domain.restaurants.repositories

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {
    suspend fun saveRestaurants(restaurants: List<Restaurant>)

    fun getRestaurants(): Flow<List<Restaurant>>
}
