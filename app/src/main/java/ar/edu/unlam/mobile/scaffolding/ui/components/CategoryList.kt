package ar.edu.unlam.mobile.scaffolding.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.utils.CategoriesMock

@Preview(showBackground = true)
@Composable
fun CategoryList() {
    val items =
        remember {
            mutableStateListOf(*CategoriesMock.categories.toTypedArray())
        }
    LazyRow(
        modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(items) { item ->
            CategoryItem(
                item = item,
                onClick = {
                    val index = items.indexOf(item)
                    if (index != -1) {
                        items[index] = item.copy(selected = !item.selected)
                        Log.d("CategoryList", "Category: ${CategoriesMock.categories[index]}")
                    }
                },
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}
