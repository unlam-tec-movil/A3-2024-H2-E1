package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.unlam.mobile.scaffolding.data.local.FoodItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen( controller: NavHostController) {
     Scaffold(
         topBar = {
             CenterAlignedTopAppBar(
                    title = { Text("Detalles del pedido") },
                    modifier = Modifier.padding(8.dp),
                    navigationIcon = {
                        IconButton(onClick = { controller.navigate("home") }) {
                            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                        }
                    }
                )
         }
     ) { paddingValue ->
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                modifier = Modifier.padding(paddingValue).padding(horizontal = 16.dp)
            ) {
                Text("Con un pedido mayor a $20.000, tenés un 10% de descuento en tu próxima compra.")
                // TODO: Reciclar lista de productos con el pedido del cliente
                FoodItem("Hamburguesa", "", 20000, 1)
                FoodItem("Hamburguesa", "", 20000, 1)
                FoodItem("Hamburguesa", "", 20000, 1)
                FoodItem("Hamburguesa", "", 20000, 1)
                FoodItem("Hamburguesa", "", 20000, 1)
            }
     }
}