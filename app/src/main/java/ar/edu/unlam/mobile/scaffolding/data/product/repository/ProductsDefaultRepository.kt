package ar.edu.unlam.mobile.scaffolding.data.product.repository

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import ar.edu.unlam.mobile.scaffolding.domain.products.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsDefaultRepository
    @Inject
    constructor(
        private val local: ProductsLocalRepository,
    ) : ProductsRepository {
        override suspend fun saveProducts(products: List<Product>) {
            local.saveProducts(products)
        }

        override fun getProducts(): Flow<List<Product>> = local.getProducts()
    }
