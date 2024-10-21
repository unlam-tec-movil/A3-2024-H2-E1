package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductsSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
            )
        },
        colors =
            OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF9F9F9),
                unfocusedBorderColor = Color.Transparent,
            ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "Buscar productos...",
                color = Color.Gray,
            )
        },
    )
}

@Preview
@Composable
fun ProductsSearchBarPreview() {
    ProductsSearchBar(searchQuery = "", onSearchQueryChange = {})
}
