package ar.edu.unlam.mobile.scaffolding.local

import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import org.junit.Assert.*
import org.junit.Test

class UserOrderRepositoryTest {
    @Test
    fun testToJson() {
        // Given
        val expected =
            """{"items":
            [{"category":"category","description":"Description","id":1,"image":"image","name":"Product","price":10.0}],
            "userTable":1}
            """.replace("\n", "").replace(" ", "").trim()
        val userOrderRepository = UserOrderRepository()
        val product = Product(1, "Product", "Description", 10.0, "image", "category")

        // When
        userOrderRepository.addItem(product)
        userOrderRepository.assignTable(1)
        val json = userOrderRepository.toJson()

        // Then
        assertEquals(expected, json)
    }

    @Test
    fun testGetTotalPrice() {
        // Given
        val expected = 30.0.toFloat()
        val userOrderRepository = UserOrderRepository()
        val product1 = Product(1, "Product1", "Description", 10.0, "image", "category")
        val product2 = Product(2, "Product2", "Description", 20.0, "image", "category")

        // When
        userOrderRepository.addItem(product1)
        userOrderRepository.addItem(product2)

        // Then
        assertEquals(expected, userOrderRepository.getTotalPrice(), 0.5f)
    }

    @Test
    fun testFromJson() {
        // Given
        val expectedUserTable = 5
        val jsonString =
            """{"items":
            |[{"category":"category","description":"Description","id":1,"image":"image","name":"Product","price":10.0}],
            |"userTable":$expectedUserTable}
            """.trimMargin()
        val userOrderRepository = UserOrderRepository()
        val expectedItems = Product(1, "Product", "Description", 10.0, "image", "category")

        // When
        userOrderRepository.fromJson(jsonString)

        // Then
        assertEquals(expectedUserTable, userOrderRepository.getTable())
        assertEquals(listOf(expectedItems), userOrderRepository.getItems())
    }
}
