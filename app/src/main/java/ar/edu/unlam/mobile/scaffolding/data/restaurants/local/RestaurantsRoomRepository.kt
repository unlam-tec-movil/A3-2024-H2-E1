package ar.edu.unlam.mobile.scaffolding.data.restaurants.local

import ar.edu.unlam.mobile.scaffolding.data.restaurants.repository.RestaurantsLocalRepository
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantsRoomRepository
    @Inject
    constructor(
        private val appDb: AppDatabase,
    ) : RestaurantsLocalRepository {
        private val restaurantDao = appDb.restaurantDao()

        override suspend fun saveRestaurants(restaurants: List<Restaurant>) {
            restaurantDao.insertRestaurants(restaurants.map { it.asEntity() })
        }

        override fun getRestaurants(): Flow<List<Restaurant>> =
            restaurantDao.getAllRestaurants().map {
                it.map { restaurantEntity ->
                    restaurantEntity.asModel()
                }
            }
    }
