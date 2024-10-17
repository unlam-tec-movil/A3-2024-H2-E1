package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun GoToMapButton(
    text: String,
    onClick: (String) -> Unit = {},
) {
    TextButton(
        modifier =
            Modifier
                .padding(4.dp),
        onClick = { onClick(text) },
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_icon_location),
            contentDescription = "Go to map",
        )
        Text(
            modifier =
                Modifier
                    .padding(6.dp),
            text = text,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoToMapButtonPreview() {
    GoToMapButton("Gaona 2340, Ramos Mejía")
}
