package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.data.restaurants.local.RestaurantsRoomRepository
import ar.edu.unlam.mobile.scaffolding.data.restaurants.repository.RestaurantsDefaultRepository
import ar.edu.unlam.mobile.scaffolding.data.restaurants.repository.RestaurantsLocalRepository
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.repositories.RestaurantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModules {
    @Binds
    abstract fun bindRestaurantsRepository(restaurantsRepositoryImpl: RestaurantsDefaultRepository): RestaurantsRepository

    @Binds
    abstract fun bindLocalRestaurantsRepository(localRestaurantsRepository: RestaurantsRoomRepository): RestaurantsLocalRepository
}
