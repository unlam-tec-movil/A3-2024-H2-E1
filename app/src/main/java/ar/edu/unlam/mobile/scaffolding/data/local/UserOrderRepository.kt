package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product

class UserOrderRepository {
    private var items: ArrayList<Product> = arrayListOf()

    /**
     * Calculate total price based on the list of products selected
     */
    fun getTotalPrice(): Float {
        var total = 0f
        for (item in items) {
            total += item.price.toFloat()
        }
        return total
    }

    fun getItems(): List<Product> = items

    fun addItem(product: Product) {
        items.add(product)
    }
}
