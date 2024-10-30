package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.CameraButton
import ar.edu.unlam.mobile.scaffolding.ui.components.TableManualInput

@Composable
fun AssignedTableScreen(
    modifier: Modifier = Modifier,
    viewModel: AssignedTableScreenViewModel = hiltViewModel(),
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        CameraButton(
            modifier =
                Modifier
                    .width(296.dp)
                    .height(370.dp),
            onClick = {},
        )
        Spacer(modifier = Modifier.height(24.dp))
        TableManualInput(
            title = "O ingresálo manualmente:",
            onChangeTable = { viewModel.onChangeTable(it) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssignedTableScreenPreview() {
    AssignedTableScreen(modifier = Modifier, viewModel = AssignedTableScreenViewModel())
}
