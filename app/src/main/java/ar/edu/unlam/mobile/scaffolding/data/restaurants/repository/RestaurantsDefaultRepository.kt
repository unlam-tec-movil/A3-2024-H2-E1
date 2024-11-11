package ar.edu.unlam.mobile.scaffolding.data.restaurants.repository

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.repositories.RestaurantsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantsDefaultRepository
    @Inject
    constructor(
        private val local: RestaurantsLocalRepository,
    ) : RestaurantsRepository {
        override suspend fun saveRestaurants(restaurants: List<Restaurant>) {
            local.saveRestaurants(restaurants)
        }

        override fun getRestaurants(): Flow<List<Restaurant>> = local.getRestaurants()
    }
