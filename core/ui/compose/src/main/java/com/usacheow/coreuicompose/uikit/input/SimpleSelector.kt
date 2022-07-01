package com.usacheow.coreuicompose.uikit.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme

object SimpleSelectorConfig {

    val MinWidth = SimpleTextFieldConfig.MinWidth
    val MinHeight = SimpleTextFieldConfig.MinHeight
    val IconSize = 20.dp
    val Padding = 16.dp

    @Composable
    fun shape() = SimpleTextFieldConfig.shape()
}

@Composable
fun SimpleSelector(
    placeholder: TextValue,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SimpleSelector(onClick = onClick, modifier = modifier) {
        SimpleSelectorPlaceholder(placeholder)
    }
}

@Composable
fun SimpleSelector(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val shape = SimpleSelectorConfig.shape()

    Row(
        modifier = modifier
            .background(AppTheme.specificColorScheme.surfaceVariant, shape)
            .sizeIn(minHeight = SimpleSelectorConfig.MinHeight, minWidth = SimpleSelectorConfig.MinWidth)
            .clip(shape)
            .doOnClick(onClick = onClick)
            .padding(SimpleSelectorConfig.Padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val contentModifier = Modifier.weight(1f)
        Box(modifier = contentModifier) { content() }
        Icon(
            imageVector = Icons.Default.ExpandMore,
            contentDescription = "Selector indicator",
            modifier = Modifier.size(SimpleSelectorConfig.IconSize),
            tint = AppTheme.specificColorScheme.symbolSecondary,
        )
    }
}

@Composable
fun SimpleSelectorPlaceholder(value: TextValue, icon: IconValue? = null) {
    DefaultSelectorItem(value = value, icon = icon)
}

@Composable
internal fun DefaultSelectorItem(
    modifier: Modifier = Modifier,
    value: TextValue,
    icon: IconValue? = null,
    color: Color = AppTheme.specificColorScheme.symbolPrimary,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.get()?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(SimpleSelectorConfig.IconSize),
            )
        }
        Text(
            text = value.get(),
            style = AppTheme.typography.bodyLarge,
            color = color,
        )
    }
}