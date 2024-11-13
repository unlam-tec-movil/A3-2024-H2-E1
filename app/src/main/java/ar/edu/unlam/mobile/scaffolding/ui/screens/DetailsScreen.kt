package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.ui.components.ProductItemCard
import ar.edu.unlam.mobile.scaffolding.ui.components.SnackBarCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    controller: NavHostController,
) {
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
                    .fillMaxSize(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Con un pedido mayor a $20.000, tenés un 10% de descuento en tu próxima compra.")
            }

            LazyColumn(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(viewModel.orderProducts.size) { index ->
                    ProductItemCard(foodItem = viewModel.orderProducts[index], onClick = { viewModel.removeItem(it.id) })
                }
            }
            if (viewModel.orderProducts.size != 0)
                {
                    SnackBarCart(
                        navController = controller,
                        totalPrice = viewModel.totalPrice.value,
                        totalItems = viewModel.totalItems.value,
                    ) {
                        Text("Confirmar", color = Color.White)
                    }
                }
        }
    }
}
