package ar.edu.unlam.mobile.scaffolding.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.CameraButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanCodeScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController? = null,
    viewModel: AssignedTableScreenViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Escanea el pedido del cliente") },
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
                .fillMaxWidth()
        ) {
            CameraButton(
                modifier =
                Modifier
                    .width(296.dp)
                    .height(370.dp),
                onClick = {
                    // Aquí se agregaría la lógica para escanear el QR del cliente
                },
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón Continuar
            Button(
                onClick = {
                    // Lógica para continuar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF67B5FF)) // Definir el color del botón
            ) {
                Text(text = "Continuar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanCodeScreenPreview() {
    ScanCodeScreen(modifier = Modifier, viewModel = AssignedTableScreenViewModel())
}