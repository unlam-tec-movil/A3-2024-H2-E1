package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import coil.compose.AsyncImage

@Composable
fun ProductItemCard(
    foodItem: Product,
    onAddClick: (Product) -> Unit,
    onRemoveClick: (Product) -> Unit,
    showRemoveButton: Boolean = false,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            Box {
                AsyncImage(
                    model = foodItem.image,
                    contentDescription = foodItem.name,
                    modifier =
                        Modifier
                            .width(120.dp)
                            .height(150.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier =
                        Modifier
                            .padding(top = 6.dp)
                            .width(150.dp),
                )
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.End)
                            .padding(top = 10.dp)
                            .padding(end = 12.dp)
                            .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$${foodItem.price}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    )
                    if (showRemoveButton) {
                        Box(
                            modifier =
                                Modifier
                                    .size(28.dp)
                                    .background(
                                        color = colorResource(id = R.color.sky),
                                        shape = CircleShape,
                                    ).clickable { onRemoveClick(foodItem) },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Check icon",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    } else {
                        Box(
                            modifier =
                                Modifier
                                    .size(28.dp)
                                    .background(
                                        color = colorResource(id = R.color.sky),
                                        shape = CircleShape,
                                    ).clickable { onAddClick(foodItem) },
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
}

@Preview(showBackground = true)
@Composable
fun ProductItemCardPreview() {
    ProductItemCard(
        foodItem =
            Product(
                id = 1,
                name = "Hamburguesa c/ papas",
                description = "Deliciosa hamburguesa con papas fritas",
                price = 1200.00,
                image = "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg",
                category = "Comida rápida",
            ),
        onAddClick = {},
        onRemoveClick = {},
    )
}
