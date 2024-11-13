package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.CameraButton
import ar.edu.unlam.mobile.scaffolding.ui.components.TableManualInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignedTableScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController? = null,
    viewModel: AssignedTableScreenViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Escaneá el numero de mesa") },
                modifier = Modifier.padding(8.dp),
                navigationIcon = {
                    IconButton(onClick = { controller?.navigate(NavHostRouterPaths.DETAILS.route) }) {
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
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1F),
            ) {
                CameraButton(
                    modifier =
                        Modifier
                            .width(296.dp)
                            .height(370.dp),
                    onClick = {},
                )
                Spacer(modifier = Modifier.height(24.dp))
                TableManualInput(
                    title = "O ingresálo manualmente:",
                    onChangeTable = { viewModel.onChangeTable(it) },
                )
            }
            Button(
                onClick = { controller?.navigate(NavHostRouterPaths.CONFIRMATION.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF67B5FF)),
                modifier = Modifier.size(width = 220.dp, height = 32.dp),
            ) {
                Text(text = "Continuar")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignedTableScreenPreview() {
    AssignedTableScreen(
        modifier = Modifier,
        viewModel = AssignedTableScreenViewModel(orderRepository = UserOrderRepository()),
    )
}
