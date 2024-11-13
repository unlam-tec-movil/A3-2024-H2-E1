package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.CameraButton
import ar.edu.unlam.mobile.scaffolding.ui.components.TableManualInput
import ar.edu.unlam.mobile.scaffolding.ui.theme.tenoriteFamily
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignedTableScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController? = null,
    viewModel: AssignedTableScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var result by remember { mutableStateOf("") }
    val scanLauncher =
        rememberLauncherForActivityResult(
            contract = ScanContract(),
            onResult =
                { newResult ->
                    result = newResult.contents ?: "No results"
                },
        )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Escaneá el numero de mesa",style = TextStyle(fontFamily = tenoriteFamily, fontSize = 24.sp )) },
                modifier = Modifier.padding(8.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        controller?.navigate(NavHostRouterPaths.DETAILS.route)
                    }) {
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
                    onClick = {
                        scanLauncher.launch(ScanOptions())
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
                TableManualInput(
                    title = "O ingresálo manualmente:",
                    onChangeTable = { viewModel.onChangeTable(it) },
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text("Mesa: $result")
            }

            Button(
                onClick = {
                    if (result.isNotEmpty() || viewModel.getTable() > 0) {
                        controller?.navigate(NavHostRouterPaths.CONFIRMATION.route)
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Por favor, escaneá el código QR o ingresá el número de mesa manualmente",
                                Toast.LENGTH_SHORT,
                            ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF67B5FF)),
                modifier = Modifier.size(width = 220.dp, height = 32.dp),
            ) {
                Text(text = "Continuar", fontFamily = tenoriteFamily,)
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
