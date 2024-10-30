package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TableManualInput(
    title: String,
    onChangeTable: ((Int) -> Unit)? = null,
) {
    var tableNumber by remember { mutableStateOf(TextFieldValue()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title)

        Spacer(modifier = Modifier.width(20.dp))

        TextField(
            value = tableNumber,
            singleLine = true,
            onValueChange = {
                if (it.text.all(Char::isDigit) && it.text.length <= 2) {
                    tableNumber = it
                    it.text.toIntOrNull()?.let { number ->
                        onChangeTable?.invoke(number)
                    }
                }
            },
            modifier =
                Modifier
                    .testTag("Textfield")
                    .width(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TableManualInputPreview() {
    TableManualInput("O ingresálo manualmente:", null)
}
