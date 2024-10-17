package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import groovyjarjarpicocli.CommandLine.Help.Column
import org.gradle.internal.impldep.org.h2.result.Row
import org.jdom2.Text
import org.tensorflow.lite.schema.Padding
import java.awt.Color
import java.lang.reflect.Modifier
import javax.smartcardio.Card


data class FoodItem(
    val title: String,
    val description: String,
    val price: Int,
    val imageRes: Int
)

@Composable
fun MenuScreen(){
    val foodItems = listOf(
        FoodItem("Hamburguesa c/ papas", "Deliciosa hamburguesa con papas fritas", 1200, R.drawable.hamburguesa_con_papas),
        FoodItem("Pizza 4 quesos", "Pizza con mezcla de cuatro quesos", 1200, R.drawable.pizza),
        FoodItem("Pastel de papas", "Pastel casero de papa", 1200, R.drawable.pastel),
        FoodItem("Ñoquis de papa", "Ñoquis con salsa de tomate", 1200, R.drawable.noquis),
    )

    LazyColumn(
        modifier = Modifier.fillMxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(FoodItems.size) { index ->
            FoodItemCard(foodItem = foodItems[index])

        }
    }
}

@Composable
fun FoodItemCard(foodItem: FoodItem){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDeafults.cardElevantion(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = foodItem.imageRes),
                contenDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Aligment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = foodItem.title, style =MaterialTheme.typography.titleMedium)
                Text(text = foodItem.description, style = MaterialTheme.typography.bodySmall)
                Text(text = "$${foodItem.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}