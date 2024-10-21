package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun PedidoConfirmadoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ícono de flecha de regreso
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 0.dp)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_revert),
                contentDescription = "Back"
            )
        }

        // Texto de confirmación
        Text(
            text = "¡Pedido Confirmado!",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 2.dp)
                .fillMaxWidth(), // Ocupa el ancho completo
            textAlign = TextAlign.Center // Centrar el texto
        )

        // Ícono de tilde verde
        Icon(
            painter = painterResource(id = R.drawable.ic_check_circle),
            contentDescription = "Confirmación exitosa",
            tint = Color.Green,
            modifier = Modifier
                .size(64.dp) // Tamaño del ícono
                .padding(bottom = 16.dp)
        )
        // QR
        Image(
            painter = painterResource(id = R.drawable.qr_sample),
            contentDescription = "QR Code",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        )

        // Texto explicativo del QR
        Text(
            text = "Mostrale el QR al mozo o presentalo en caja para finalizar el pago",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center // Centrar el texto
        )

        // Botón de volver al inicio
        Button(
            onClick = { navController.navigate("inicio") },
            modifier = Modifier.padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF67B5FF), // Color de fondo (ejemplo púrpura)
                contentColor = Color.White // Color del texto
            )
        ) {
            Text(text = "Volver al inicio")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewPedidoConfirmadoScreen() {
    PedidoConfirmadoScreen(navController = rememberNavController())
}

