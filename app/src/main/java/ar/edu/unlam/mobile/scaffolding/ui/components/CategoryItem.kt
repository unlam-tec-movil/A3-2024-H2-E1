package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

data class CategoryItem(
    val id: Int,
    val thumbnail: Int,
    val selected: Boolean,
)

@Composable
fun CategoryItem(
    item: CategoryItem,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .size(82.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = colorResource(id = R.color.ligth_grey))
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color.LightGray,
                    spotColor = Color.LightGray,
                ).border(
                    width = 2.dp,
                    color = if (item.selected) colorResource(id = R.color.sky) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp),
                ).clickable { onClick() },
    ) {
        if (item.selected) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = "Check Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .padding(end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(color = colorResource(id = R.color.sky))
            )
        }
        Image(
            painter = painterResource(id = R.drawable.category_pizza),
            contentDescription = "Categoria de Comida",
            modifier =
                Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxSize()
                    .padding(8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryItem() {
    // Crear un objeto de ejemplo para CategoryItem
    val exampleItem = CategoryItem(id = 1, thumbnail = R.drawable.category_pizza, selected = true)

    // Llamar al composable con el objeto de ejemplo
    CategoryItem(item = exampleItem, onClick = {})
}
