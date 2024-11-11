package ar.edu.unlam.mobile.scaffolding.domain.products.services

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import ar.edu.unlam.mobile.scaffolding.domain.products.repositories.ProductsRepository
import ar.edu.unlam.mobile.scaffolding.domain.products.usecases.ProductsUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsService
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) : ProductsUseCases {
        override suspend fun saveProducts(products: List<Product>) {
            productsRepository.saveProducts(products)
        }

        override fun getProducts(): Flow<List<Product>> = productsRepository.getProducts()
    }
