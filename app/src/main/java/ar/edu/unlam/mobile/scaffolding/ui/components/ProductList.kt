package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product

@Composable
fun ProductList(
    onItemClick: (Product) -> Unit,
    products: List<Product>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(products.size) { index ->
            ProductItemCard(
                foodItem = products[index],
                onAddClick = { onItemClick(it) },
                onRemoveClick = {},
                showRemoveButton = false,
            )
        }
    }
}
