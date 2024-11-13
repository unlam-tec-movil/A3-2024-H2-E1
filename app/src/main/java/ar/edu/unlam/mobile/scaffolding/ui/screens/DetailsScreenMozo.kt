package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.ui.components.ProductItemCard
import ar.edu.unlam.mobile.scaffolding.ui.components.SnackBarCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenMozo(
    controller: NavHostController,
    viewModel: DetailsScreenMozoViewModel = hiltViewModel(),
    jsonOrder: String,
) {
    LaunchedEffect(key1 = jsonOrder) {
        viewModel.setOrder(jsonOrder)
    }

    val orderProducts by viewModel.orderProducts.collectAsState()

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
                    .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(orderProducts) { product ->
                    ProductItemCard(foodItem = product, onClick = { })
                }
            }

            SnackBarCart(navController = controller, totalPrice = viewModel.totalPrice.value, totalItems = viewModel.totalItems.value) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreenMozo() {
    // Usamos un controlador de navegación falso para la previsualización
    val navController = rememberNavController()

    DetailsScreenMozo(controller = navController, jsonOrder = "")
}
