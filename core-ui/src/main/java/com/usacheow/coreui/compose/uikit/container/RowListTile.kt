package com.usacheow.coreui.compose.uikit.container

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.usacheow.coreui.adapter.base.WidgetState

data class RowListItem(
    val items: List<WidgetState>,
    val contentPadding: PaddingValues = PaddingValues(),
) : WidgetState() {
    override val content = @Composable {
        RowListTile(contentPadding, items)
    }
}

@Composable
fun RowListTile(
    contentPadding: PaddingValues,
    items: List<WidgetState> = emptyList(),
) {
    LazyRow(contentPadding = contentPadding) {
        items(count = items.size) {
            items[it].content()
        }
    }
}