package ar.edu.unlam.mobile.scaffolding.domain.products.models

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)
