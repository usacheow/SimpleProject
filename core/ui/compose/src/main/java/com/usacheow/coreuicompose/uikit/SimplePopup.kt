package com.usacheow.coreuicompose.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.uikit.input.DefaultSelectorItem
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorConfig
import com.usacheow.coreuicompose.uikit.input.SimpleTextFieldConfig
import com.usacheow.coreuitheme.compose.AppTheme

object SimplePopupConfig {

    @Composable
    fun shape() = SimpleTextFieldConfig.shape()
}

@Composable
fun SimplePopup(
    modifier: Modifier = Modifier,
    offset: IntOffset = IntOffset(0, 0),
    shape: Shape = SimplePopupConfig.shape(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Popup(
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(focusable = true),
        offset = offset,
    ) {
        Column(
            modifier = Modifier
                .border(width = .5f.dp, color = AppTheme.specificColorScheme.outline, shape = shape)
                .clip(shape)
                .background(AppTheme.specificColorScheme.surface)
                .then(modifier),
        ) {
            content()
        }
    }
}

@Composable
fun SimplePopupHeader(
    value: TextValue,
) {
    DefaultSelectorItem(
        modifier = Modifier.padding(SimpleSelectorConfig.Padding),
        value = value,
        color = AppTheme.specificColorScheme.symbolSecondary,
    )
}

@Composable
fun SimplePopupItem(
    modifier: Modifier = Modifier,
    value: TextValue,
    icon: IconValue? = null,
    onClick: () -> Unit,
) {
    DefaultSelectorItem(
        modifier = Modifier
            .fillMaxWidth()
            .doOnClick(onClick = onClick)
            .padding(horizontal = SimpleSelectorConfig.Padding, vertical = SimpleSelectorConfig.Padding / 2)
            .then(modifier),
        value = value,
        icon = icon
    )
}