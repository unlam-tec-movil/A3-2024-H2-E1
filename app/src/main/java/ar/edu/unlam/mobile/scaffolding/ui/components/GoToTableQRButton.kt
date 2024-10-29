package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun GoToTableQRButton(
    text: String,
    onClick: (String) -> Unit = {},
) {
    IconButton(
        onClick = { onClick(text) },
    ) {
        Image(
            painter = painterResource(id = R.drawable.scan_icon),
            contentDescription = "Go to map",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoToTableQRButtonPreview() {
    GoToTableQRButton("Scan QR")
}
