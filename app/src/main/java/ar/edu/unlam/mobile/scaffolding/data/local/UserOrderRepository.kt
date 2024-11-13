package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class UserOrderRepository : Serializable {
    private var userTable: Int = 0
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

    fun removeItem(id: Int) {
        val itemToRemove = items.firstOrNull { it.id == id }
        if (itemToRemove != null) {
            items.remove(itemToRemove)
        }
    }

    fun removeAllItems() {
        items.clear()
    }

    fun assignTable(number: Int) {
        userTable = number
    }

    fun getTable(): Int = userTable

    /**
     * Convert the repository to a JSON string
     *
     * example result: {"userTable":1,"items":[{"id":1,"name":"Product","price":10}]}
     *
     */
    fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    /**
     * Parse the repository from a JSON string
     */
    fun fromJson(jsonString: String) {
        val gson = Gson()
        val type = object : TypeToken<UserOrderRepository>() {}.type
        val repository: UserOrderRepository = gson.fromJson(jsonString, type)
        this.userTable = repository.userTable
        this.items.clear()
        this.items.addAll(repository.items)
    }
}
