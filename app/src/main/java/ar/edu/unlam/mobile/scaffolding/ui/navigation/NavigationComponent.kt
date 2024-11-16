package ar.edu.unlam.mobile.scaffolding.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.screens.assignedTable.AssignedTableScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.details.DetailsScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.detailsMozo.DetailsScreenMozo
import ar.edu.unlam.mobile.scaffolding.ui.screens.home.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.map.MapScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.orderConfirmation.OrderConfirmationScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.scanCode.ScanCodeScreen

@Composable
fun NavigationComponent() {
    val controller = rememberNavController()
    Scaffold { paddingValue ->
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
                AssignedTableScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller,
                )
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
