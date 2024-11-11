package ar.edu.unlam.mobile.scaffolding.domain.products.usecases

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsUseCases {
    suspend fun saveProducts(products: List<Product>)

    fun getProducts(): Flow<List<Product>>
}
