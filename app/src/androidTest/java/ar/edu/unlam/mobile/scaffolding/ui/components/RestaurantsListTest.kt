package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import ar.edu.unlam.mobile.scaffolding.ui.screens.map.RestaurantsList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantsListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun restaurantsListComponentTest() {
        // Given
        val restaurantsList =
            listOf(
                Restaurant(
                    id = 1,
                    address = "Av. Corrientes 1234",
                    latitude = -34.66731623327424,
                    longitude = -58.55944707688669,
                    calculateDistance = 5.45f,
                ),
                Restaurant(
                    id = 2,
                    address = "Av. Rivadavia 1234",
                    latitude = -34.67006049259394,
                    longitude = -58.565144088772854,
                    calculateDistance = 10.5f,
                ),
            )
        // When
        composeTestRule.setContent {
            RestaurantsList(restaurants = restaurantsList)
        }
        // Then
        composeTestRule.onNodeWithText("Av. Corrientes 1234").assertIsDisplayed()
        composeTestRule.onNodeWithText("Av. Rivadavia 1234").assertIsDisplayed()

        composeTestRule.onNodeWithText("5.4 km").assertIsDisplayed()
        composeTestRule.onNodeWithText("10.5 km").assertIsDisplayed()
    }
}
