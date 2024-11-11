package ar.edu.unlam.mobile.scaffolding.utils

data class Category(
    val id: Int,
    val name: String,
    val image: String,
    val selected: Boolean,
)

object CategoriesMock {
    val categories =
        mutableListOf(
            // lista de categorias platos restaurant
            Category(
                id = 1,
                name = "Milanesas",
                image =
                    @Suppress("ktlint:standard:max-line-length")
                    "https://assets.tmecosys.com/image/upload/t_web767x639/img/"+
                        "recipe/ras/Assets/" +
                        "7fd9e77b-3396-49f3-be08-fceef56376bf/Derivates" +
                        "/8a096b0f-382a-47ac-b11a-62b630e0e59e.jpg",
                selected = false,
            ),
            Category(
                id = 2,
                name = "Pizzas",
                image =
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTI2hdQeNVlyu" +
                        "20ReOpJcNwdgW0ER5hwxnauQ&s",
                selected = false,
            ),
            Category(
                id = 3,
                name = "Pastas",
                image =
                    "https://conzazoni.com/wp-content/uploads/2023/05/recetas-" +
                        "especiales-con-pastas-conzazoni.webp",
                selected = false,
            ),
            Category(
                id = 4,
                name = "Postres",
                image =
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
                        "ANd9GcRRYvCPseOQTqpTWGJNCwmb4ZXQ-T-coeOHfQ&s",
                selected = false,
            ),
            Category(
                id = 5,
                name = "Bebidas",
                image =
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
                        "ANd9GcRUpraU7RJhGhyvtTIKsxlpj2j9sz0tuLlwVg&s",
                selected = false,
            ),
            Category(
                id = 6,
                name = "Carnes",
                image =
                    "https://cloudfront-us-east-1.images.arcpublishing.com/" +
                        "infobae/XTWCVOJ5LZCTFDSMDYIVGMDXCU.jpg",
                selected = false,
            ),
            Category(
                id = 7,
                name = "Empanadas",
                image =
                    "https://www.recetasnatura.com.ar/sites/default/files/styles/receta_cuerpo/" +
                        "public/655_x_475-_" +
                        "web_naturaempanadas_de_roastbeef_desmenuzado.jpg?itok=8iHtVcq3",
                selected = false,
            ),
        )
}
