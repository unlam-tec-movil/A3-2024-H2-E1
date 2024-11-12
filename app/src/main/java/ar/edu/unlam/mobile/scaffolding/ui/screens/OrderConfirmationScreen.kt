package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.NavHostRouterPaths
import ar.edu.unlam.mobile.scaffolding.R
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(
    controller: NavController,
    viewModel: OrderConfirmationScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    fun saveImageTemporarily(
        context: Context,
        data: Intent?,
    ): Uri? {
        val photoFile = File(context.cacheDir, "captured_photo.jpg")
        val photoUri =
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                photoFile,
            )

        // Guarda la imagen en el archivo temporal
        data?.extras?.get("data")?.let { bitmap ->
            FileOutputStream(photoFile).use { out ->
                (bitmap as Bitmap).compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
            }
        }
        return photoUri
    }

    fun shareImage(
        context: Context,
        imageUri: Uri,
    ) {
        val shareIntent =
            Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, imageUri)
            }
        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(shareIntent, "Compartir imagen en:"))
        } else {
            Toast
                .makeText(
                    context,
                    "No hay aplicaciones instaladas para compartir",
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == android.app.Activity.RESULT_OK) {
                val photoUri = saveImageTemporarily(context, result.data)
                photoUri?.let {
                    shareImage(context, it)
                }
            } else {
                Toast.makeText(context, "No se capturó ninguna foto", Toast.LENGTH_SHORT).show()
            }
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted ->
            if (isGranted) {
                launcher.launch(cameraIntent)
            } else {
                Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                modifier = Modifier.padding(8.dp),
            )
        },
    ) { paddingValue ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValue)
                    .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Texto de confirmación
            Text(
                text = "¡Pedido Confirmado!",
                style = TextStyle(fontSize = 24.sp),
                modifier =
                    Modifier
                        .padding(top = 8.dp, bottom = 2.dp)
                        .fillMaxWidth(),
                // Ocupa el ancho completo
                textAlign = TextAlign.Center, // Centrar el texto
            )

            // Ícono de tilde verde
            Icon(
                painter = painterResource(id = R.drawable.ic_check_circle),
                contentDescription = "Confirmación exitosa",
                tint = Color.Green,
                modifier =
                    Modifier
                        .size(64.dp) // Tamaño del ícono
                        .padding(bottom = 16.dp),
            )
            // QR
            Image(
                painter = painterResource(id = R.drawable.qr_sample),
                contentDescription = "QR Code",
                contentScale = ContentScale.FillWidth,
                modifier =
                    Modifier
                        .size(200.dp)
                        .padding(16.dp),
            )

            Button(
                onClick = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                },
                modifier = Modifier.padding(16.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF67B5FF),
                        contentColor = Color.White,
                    ),
            ) {
                Text(text = "Compartí con un amigo")
            }

            // Texto explicativo del QR
            Text(
                text = "Mostrale el QR al mozo o presentalo en caja para finalizar el pago",
                style = TextStyle(fontSize = 16.sp),
                modifier =
                    Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                textAlign = TextAlign.Center, // Centrar el texto
            )

            // Botón de volver al inicio
            Button(
                onClick = { controller.navigate(NavHostRouterPaths.HOME.route) },
                modifier = Modifier.padding(vertical = 16.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF67B5FF), // Color de fondo (ejemplo púrpura)
                        contentColor = Color.White, // Color del texto
                    ),
            ) {
                Text(text = "Volver al inicio")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmationScreenPreview() {
    OrderConfirmationScreen(controller = rememberNavController())
}
