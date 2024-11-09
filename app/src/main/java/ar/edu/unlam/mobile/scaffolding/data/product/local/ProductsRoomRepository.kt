package ar.edu.unlam.mobile.scaffolding.data.product.local

import ar.edu.unlam.mobile.scaffolding.data.product.repository.ProductsLocalRepository
import ar.edu.unlam.mobile.scaffolding.data.restaurants.local.AppDatabase
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRoomRepository
    @Inject
    constructor(
        private val appDb: AppDatabase,
    ) : ProductsLocalRepository {
        private val productsDao = appDb.productDao()

        override suspend fun saveProducts(products: List<Product>) {
            productsDao.insertProducts(products.map { it.asEntity() })
        }

        override fun getProducts(): Flow<List<Product>> =
            productsDao.getAllProducts().map {
                it.map { productEntity ->
                    productEntity.asModel()
                }
            }
    }
