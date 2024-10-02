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
                contentDescription = "Search"
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLeadingIconColor = Color(0xFFA6A6A6),
            unfocusedContainerColor = Color(0xFFF9F9F9),
            unfocusedBorderColor = Color(0xFFF9F9F9),
            focusedLeadingIconColor = Color(0xFFA6A6A6),
            focusedContainerColor = Color(0xFFF9F9F9),
            focusedBorderColor = Color(0xFFF9F9F9),
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Buscar productos...") }
    )

}

