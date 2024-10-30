package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun SnackBarCart(navController: NavHostController) {
   Box (
       Modifier.background(colorResource(id = R.color.white)).shadow(elevation = 3.dp, spotColor = Color.Cyan).padding(24.dp)){
     Row (
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.Center
     ){

         Column {
             Text("$2400", color = Color(0XFF67B5FF), style = TextStyle(fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
             Text("2 items", color = Color.Gray, style = TextStyle(fontSize = 12.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
         }
         Spacer(modifier = Modifier.weight(1f))
         Button(
             onClick = { navController.navigate("details") },
             colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF67B5FF)),
             modifier = Modifier.size(width = 220.dp, height = 32.dp)
             ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.White, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text("Ir al carrito", color = Color.White)
                }
            }
     }

   }
}

@Preview
@Composable
fun SnackBarCartPreview() {
    SnackBarCart(navController = rememberNavController())
}