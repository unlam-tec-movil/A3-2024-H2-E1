package ar.edu.unlam.mobile.scaffolding.ui.screens.scanCode

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.CameraButton
import ar.edu.unlam.mobile.scaffolding.ui.navigation.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.ui.screens.assignedTable.AssignedTableScreenViewModel
import ar.edu.unlam.mobile.scaffolding.ui.theme.tenoriteFamily
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanCodeScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController? = null,
    viewModel: AssignedTableScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var jsonString by remember { mutableStateOf("") }
    val scanLauncher =
        rememberLauncherForActivityResult(
            contract = ScanContract(),
            onResult = { newResult ->
                jsonString = newResult.contents ?: "No results"
                println("result: $jsonString")
                val encodedJson = URLEncoder.encode(jsonString, StandardCharsets.UTF_8.toString())
                controller?.navigate("${NavHostRouterPaths.WAITER_DETAILS.route}/$encodedJson")
            },
        )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Escanea el pedido del cliente", style = TextStyle(fontFamily = tenoriteFamily, fontSize = 24.sp)) },
                modifier = Modifier.padding(8.dp),
            )
        },
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier =
                Modifier
                    .padding(paddingValue)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
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

            Spacer(modifier = Modifier.weight(1f))

            // Botón Continuar
            Button(
                onClick = {
                    // Lógica para continuar
                    val jsonString =
                        """{"items":
                        |[{"category":"category","description":"Description","id":1,
                        |"image":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3QjzVgP1LiWdTQKVa_nmlMOEjOTzBKFNhrQ&s","name":"Product","price":10.0}],
                        |"userTable":5}
                        """.trimMargin()
                    val encodedJson = URLEncoder.encode(jsonString, StandardCharsets.UTF_8.toString())
                    controller?.navigate("${NavHostRouterPaths.WAITER_DETAILS.route}/$encodedJson")
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF67B5FF)), // Definir el color del botón
            ) {
                Text(text = "Continuar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanCodeScreenPreview() {
    ScanCodeScreen(modifier = Modifier, viewModel = AssignedTableScreenViewModel(orderRepository = UserOrderRepository()))
}
