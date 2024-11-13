package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.screens.AssignedTableScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.DetailsScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.DetailsScreenMozo
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.OrderConfirmationScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.ScanCodeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.map.MapScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val controller = rememberNavController()
    Scaffold(
//        bottomBar = { SnackBarCart(navController = controller) },
//        bottomBar = { BottomBar(controller = controller) }, // Si tienes una barra de navegación
        floatingActionButton = {
//            IconButton(onClick = { controller.navigate("home") }) {
//                // Navegación a HomeScreen
//                Icon(Icons.Filled.Home, contentDescription = "Home")
//            }
        },
    ) { paddingValue ->
        NavHost(navController = controller, startDestination = "home") {
            composable(NavHostRouterPaths.HOME.route) {
                // Home es el componente en sí que es el destino de navegación.
                HomeScreen(modifier = Modifier.padding(paddingValue), controller = controller)
            }
            composable(NavHostRouterPaths.MAP.route) {
                MapScreen(onBackClick = { controller.navigate("home") })
            }
            composable(NavHostRouterPaths.DETAILS.route) {
                DetailsScreen(controller = controller)
            }
            composable(NavHostRouterPaths.ASSIGNED_TABLE.route) {
                AssignedTableScreen(modifier = Modifier.padding(paddingValue), controller = controller)
            }
            composable(NavHostRouterPaths.CONFIRMATION.route) {
                OrderConfirmationScreen(controller = controller)
            }
            composable(NavHostRouterPaths.SCAN_ORDER_CODE.route) {
                ScanCodeScreen(controller = controller)
            }
            composable("${NavHostRouterPaths.WAITER_DETAILS.route}/{json}") { backStackEntry ->
                val jsonString = backStackEntry.arguments?.getString("json")
                jsonString?.let {
                    DetailsScreenMozo(controller = controller, jsonOrder = it)
                }
            }
        }
    }
}
