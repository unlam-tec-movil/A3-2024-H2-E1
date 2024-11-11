package ar.edu.unlam.mobile.scaffolding.data.product.repository

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsLocalRepository {
    suspend fun saveProducts(products: List<Product>)

    fun getProducts(): Flow<List<Product>>
}
