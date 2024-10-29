package ar.edu.unlam.mobile.scaffolding.domain.restaurants.usecases

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCases {
    suspend fun getRestaurants(): Flow<List<Restaurant>>
}
