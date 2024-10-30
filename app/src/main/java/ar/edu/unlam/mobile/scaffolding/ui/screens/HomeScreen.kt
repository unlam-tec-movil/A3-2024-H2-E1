package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.data.local.MenuScreen
import ar.edu.unlam.mobile.scaffolding.ui.components.CategoryList
import ar.edu.unlam.mobile.scaffolding.ui.components.GoToMapButton
import ar.edu.unlam.mobile.scaffolding.ui.components.GoToTableQRButton
import ar.edu.unlam.mobile.scaffolding.ui.components.ProductsSearchBar
import ar.edu.unlam.mobile.scaffolding.ui.components.SnackBarCart

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    controller: NavController,
) {
    var isSnackBarVisible by remember { mutableStateOf(false) }
    val bottomPadding = if (isSnackBarVisible) 66.dp else 0.dp

    fun showSnackCart() {
        isSnackBarVisible = !isSnackBarVisible
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
                ProductsSearchBar(searchQuery = "", onSearchQueryChange = {})
            }
            Box {
                Column {
                    CategoryList()
                    MenuScreen(showSnackCart = ::showSnackCart)
                }
            }
        }

        AnimatedVisibility(
            visible = isSnackBarVisible,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            SnackBarCart(navController = controller)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        modifier = TODO(),
        viewModel = TODO(),
        controller = TODO(),
    )
}
