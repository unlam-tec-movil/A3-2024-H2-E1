package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.FoodItem
import ar.edu.unlam.mobile.scaffolding.data.local.FoodItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(controller: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalles del pedido") },
                modifier = Modifier.padding(8.dp),
                navigationIcon = {
                    IconButton(onClick = { controller.navigate(NavHostRouterPaths.HOME.route) }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
            )
        },
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
                Modifier
                    .padding(paddingValue)
                    .padding(horizontal = 16.dp),
        ) {
            Text("Con un pedido mayor a $20.000, tenés un 10% de descuento en tu próxima compra.")
            // TODO: Arreglar el food items para que muestre los que correspondan, ahora lo agregué del otro para que quede maquetado
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
                    FoodItemCard(foodItem = foodItems[index], showSnackCart = {})
                }
            }
        }
    }
}
