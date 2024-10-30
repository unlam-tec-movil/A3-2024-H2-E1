package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun CameraButton(
    modifier: Modifier,
    onClick: () -> Unit = {},
) {
    FilledIconButton(
        onClick = { onClick() },
        shape = RectangleShape,
        modifier = modifier,
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent),
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.camera_button),
            contentScale = ContentScale.Crop,
            contentDescription = "Go to map",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CameraButtonPreview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        CameraButton(
            modifier =
                Modifier
                    .width(300.dp)
                    .height(500.dp),
            onClick = {},
        )
    }
}
