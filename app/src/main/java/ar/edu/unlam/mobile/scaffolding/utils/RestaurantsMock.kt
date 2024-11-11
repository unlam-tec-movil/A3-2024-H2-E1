package ar.edu.unlam.mobile.scaffolding.utils

import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant

object RestaurantsMock {
    val restaurantsList =
        listOf(
            Restaurant(
                id = 1,
                address = "Av. Corrientes 1234",
                latitude = -34.66731623327424,
                longitude = -58.55944707688669,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 2,
                address = "Av. Rivadavia 1234",
                latitude = -34.67006049259394,
                longitude = -58.565144088772854,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 3,
                address = "Av. Belgrano 1234",
                latitude = -34.67346642437504,
                longitude = -58.56366350941966,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 4,
                address = "Av. Callao 1234",
                latitude = -34.672072303449035,
                longitude = -58.570132997495286,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 5,
                address = "Av. Santa Fe 1234",
                latitude = -34.67061639257289,
                longitude = -58.562805202537106,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 6,
                address = "Av. Córdoba 1234",
                latitude = -34.598000,
                longitude = -58.420000,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 7,
                address = "Av. 9 de Julio 1234",
                latitude = -34.603722,
                longitude = -58.381592,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 8,
                address = "Av. Pueyrredón 1234",
                latitude = 34.594000,
                longitude = -58.400000,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 9,
                address = "Av. Scalabrini Ortiz 1234",
                latitude = -34.589000,
                longitude = -58.430000,
                calculateDistance = 0f,
            ),
            Restaurant(
                id = 10,
                address = "Av. Juan B. Justo 1234",
                latitude = -34.598000,
                longitude = -58.450000,
                calculateDistance = 0f,
            ),
        )
}
