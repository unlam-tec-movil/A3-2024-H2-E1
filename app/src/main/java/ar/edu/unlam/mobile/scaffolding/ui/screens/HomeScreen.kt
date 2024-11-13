package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.Manifest
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import ar.edu.unlam.mobile.scaffolding.ui.components.CategoryList
import ar.edu.unlam.mobile.scaffolding.ui.components.GoToMapButton
import ar.edu.unlam.mobile.scaffolding.ui.components.GoToTableQRButton
import ar.edu.unlam.mobile.scaffolding.ui.components.ProductList
import ar.edu.unlam.mobile.scaffolding.ui.components.ProductsSearchBar
import ar.edu.unlam.mobile.scaffolding.ui.components.SnackBarCart

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    controller: NavHostController,
) {
    val context = LocalContext.current
    val uiState: HomeUIState by viewModel.uiState.collectAsState()

    val bottomPadding = if (viewModel.isSnackBarVisible.value) 66.dp else 0.dp
    var showAgentCodeDialog = remember { mutableStateOf(false) }
    var isShakeDetected = remember { mutableStateOf(false) }

    fun onSearchQueryChange(query: String) {
        viewModel.filterProducts(query)
    }

    // Sensor Manager and Listener for Shake Detection
    val sensorManager =
        remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val shakeListener =
        remember {
            object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    // Simple shake detection logic with a threshold
                    val shakeThreshold = 15f
                    if (x * x + y * y + z * z > shakeThreshold * shakeThreshold) {
                        isShakeDetected.value = true
                    }
                }

                override fun onAccuracyChanged(
                    sensor: Sensor,
                    accuracy: Int,
                ) {
                }
            }
        }

    fun showSnackCart(product: Product) {
        println("onItemClick: ${product.id} - ${product.name}")
        viewModel.addProduct(product)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { granted ->
            if (granted) {
                controller.navigate("map")
            } else {
                // Denied
            }
        }

    DisposableEffect(Unit) {
        sensorManager.registerListener(
            shakeListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI,
        )
        onDispose {
            sensorManager.unregisterListener(shakeListener)
        }
    }

    if (isShakeDetected.value) {
        showAgentCodeDialog.value = true
        isShakeDetected.value = false
    }

    if (showAgentCodeDialog.value) {
        AgentCodeDialog(
            onDismiss = { showAgentCodeDialog.value = false },
            onCodeEntered = { code: String ->
                if (code == "1234") {
                    controller.navigate(NavHostRouterPaths.SCAN_ORDER_CODE.route)
                }
            },
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(bottom = bottomPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                GoToMapButton(
                    text = "Gaona 2340, Ramos Mejia",
                    onClick = {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    },
                )
                GoToTableQRButton(text = "SCAN QR")
            }
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
            ) {
                ProductsSearchBar(
                    searchQuery = viewModel.searchQuery,
                    onSearchQueryChange = { onSearchQueryChange(it) },
                )
            }
            Box {
                Column {
                    CategoryList()
                    when (val productsState = uiState.productsState) {
                        is ProductsUIState.Success -> {
                            ProductList(
                                onItemClick = ::showSnackCart,
                                products = productsState.products,
                            )
                        }

                        is ProductsUIState.Loading -> {
                            // Loading
                        }

                        is ProductsUIState.Error -> {
                            // Error
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = viewModel.isSnackBarVisible.value,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            SnackBarCart(navController = controller, totalPrice = viewModel.totalPrice.value, totalItems = viewModel.totalItems.value) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text("Ir al carrito", color = Color.White)
            }
        }
    }
}

// Dialogo para ingresar el código de agente
@Composable
fun AgentCodeDialog(
    onDismiss: () -> Unit,
    onCodeEntered: (String) -> Unit,
) {
    var code = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Código de Agente") },
        text = {
            TextField(
                value = code.value,
                onValueChange = { code.value = it },
                label = { Text("Ingrese el código") },
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onCodeEntered(code.value) // Pasar el código ingresado
                onDismiss() // Cerrar el diálogo
            }) {
                Text("Ingresar") // Texto del botón
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController() // Crear el navController
    HomeScreen(
        modifier = Modifier,
        viewModel = hiltViewModel(),
        controller = navController,
    )
}
