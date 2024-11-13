package ar.edu.unlam.mobile.scaffolding.data.restaurants.repository

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantsDefaultRepositoryTest {
    @Mock
    private lateinit var local: RestaurantsLocalRepository

    @InjectMocks
    private lateinit var subject: RestaurantsDefaultRepository

    @Test
    fun listRestaurantsSuccess() {
        // Given
        val restaurantStub =
            Restaurant(
                id = 1,
                address = "Av. Siempre Viva 742",
                latitude = -34.603722,
                longitude = -58.381592,
                calculateDistance = 0.0f,
            )
        val expected = flowOf(listOf(restaurantStub))
        Mockito.`when`(local.getRestaurants()).thenReturn(expected)
        // When
        val actual = subject.getRestaurants()
        // Then
        Assert.assertEquals(expected, actual)
    }
}
