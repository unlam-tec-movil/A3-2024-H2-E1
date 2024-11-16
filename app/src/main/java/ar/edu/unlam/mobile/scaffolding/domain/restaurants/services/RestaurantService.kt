package ar.edu.unlam.mobile.scaffolding.domain.restaurants.services

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.repositories.RestaurantsRepository
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.usecases.RestaurantUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantService
    @Inject
    constructor(
        private val restaurantsRepository: RestaurantsRepository,
    ) : RestaurantUseCases {
        override suspend fun saveRestaurants(restaurants: List<Restaurant>) {
            restaurantsRepository.saveRestaurants(restaurants)
        }

        override fun getRestaurants(): Flow<List<Restaurant>> = restaurantsRepository.getRestaurants()
    }
