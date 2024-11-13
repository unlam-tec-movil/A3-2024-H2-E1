package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.theme.tenoriteFamily

@Composable
fun WaiterSnackBarCart(
    totalPrice: Double = 0.00,
    totalItems: Int = 0,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        Modifier
            .background(colorResource(id = R.color.white))
            .shadow(elevation = 3.dp, spotColor = Color.Cyan)
            .padding(24.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Column {
                Text(
                    "$$totalPrice",
                    color = Color(0XFF67B5FF),
                    style =
                    TextStyle(
                        fontSize = 24.sp,
                        fontFamily = tenoriteFamily,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Text(
                    "$totalItems items",
                    color = Color.Gray,
                    style =
                    TextStyle(
                        fontSize = 12.sp,
                        fontFamily = tenoriteFamily,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onClick.invoke() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF67B5FF)),
                modifier = Modifier.size(width = 220.dp, height = 32.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    content()
                }
            }
        }
    }
}