package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

data class FoodItem(
    val title: String,
    val description: String,
    val price: Int,
    val imageRes: Int,
)

@Composable
fun MenuScreen(showSnackCart: () -> Unit) {
    val foodItems =
        listOf(
            FoodItem(
                "Hamburguesa c/ papas",
                "Deliciosa hamburguesa con papas fritas",
                1200,
                R.drawable.hamburguesa_con_papas,
            ),
            FoodItem("Pizza 4 quesos", "Pizza con mezcla de cuatro quesos", 1200, R.drawable.pizza),
            FoodItem("Pastel de papas", "Pastel casero de papa", 1200, R.drawable.pastel),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
            FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
        )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(foodItems.size) { index ->
            FoodItemCard(foodItem = foodItems[index], showSnackCart = showSnackCart)
        }
    }
}

@Composable
fun FoodItemCard(
    foodItem: FoodItem,
    showSnackCart: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
        ) {
            Box {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = foodItem.imageRes),
                    contentDescription = "Item comida",
                    modifier =
                        Modifier
                            .size(120.dp),
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = foodItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 6.dp).width(150.dp),
                )
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.End)
                            .padding(top = 10.dp)
                            .padding(end = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$${foodItem.price}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    )
                    Box(
                        modifier =
                            Modifier
                                .size(28.dp)
                                .background(
                                    color = colorResource(id = R.color.sky),
                                    shape = CircleShape,
                                ).clickable { showSnackCart() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_icon),
                            contentDescription = "Check icon",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
            }
        }
    }
}
