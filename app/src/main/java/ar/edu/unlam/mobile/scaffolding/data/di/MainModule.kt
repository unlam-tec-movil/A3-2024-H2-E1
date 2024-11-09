package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.domain.products.services.ProductsService
import ar.edu.unlam.mobile.scaffolding.domain.products.usecases.ProductsUseCases
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.services.RestaurantService
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.usecases.RestaurantUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {
    @Binds
    abstract fun bindRestaurant(restaurantService: RestaurantService): RestaurantUseCases

    @Binds
    abstract fun bindProducts(productsService: ProductsService): ProductsUseCases
}
