package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenMozo(controller: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalles del pedido") },
                modifier = Modifier.padding(8.dp),
                navigationIcon = {
                    IconButton(onClick = { controller.navigate(NavHostRouterPaths.HOME.route) }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                        )
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
//                items(foodItems.size) { index ->
//                    FoodItemCard(foodItem = foodItems[index], showSnackCart = {})
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreenMozo() {
    // Usamos un controlador de navegación falso para la previsualización
    val navController = rememberNavController()

    DetailsScreenMozo(controller = navController)
}
