package ar.edu.unlam.mobile.scaffolding.data.product.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)

fun ProductEntity.asModel() =
    Product(
        id = id,
        name = name,
        description = description,
        price = price,
        image = image,
        category = category,
    )

fun Product.asEntity() =
    ProductEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        image = image,
        category = category,
    )
