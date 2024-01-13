package com.usacheow.coreuicompose.uikit.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.inset
import com.usacheow.coreuicompose.tools.insetTop
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun SheetIndicator() {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(4.dp)
            .background(AppTheme.specificColorScheme.onSurface1, RoundedCornerShape(2.dp)),
    )
}

@Composable
fun SheetContainer(
    offset: Dp,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .inset(insetTop())
            .padding(top = offset)
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(AppTheme.specificColorScheme.surface1),
        content = content,
    )
}