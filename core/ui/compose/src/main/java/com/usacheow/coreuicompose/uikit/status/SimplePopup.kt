package com.usacheow.coreuicompose.uikit.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.addIf
import com.usacheow.coreuicompose.tools.defaultBorder
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.uikit.input.DefaultSelectorItem
import com.usacheow.coreuicompose.uikit.input.SimpleSelectorConfig
import com.usacheow.coreuicompose.uikit.input.SimpleTextFieldConfig
import com.usacheow.coreuitheme.compose.AppTheme

object SimplePopupConfig {

    @Composable
    fun shape() = SimpleTextFieldConfig.shape()

    @Composable
    fun backgroundColor() = AppTheme.specificColorScheme.surface
}

@Composable
fun SimplePopup(
    modifier: Modifier = Modifier,
    offset: IntOffset = IntOffset(0, 0),
    shape: Shape = SimplePopupConfig.shape(),
    needBackground: Boolean = true,
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
                .addIf(needBackground) {
                    defaultBorder(shape)
                        .clip(shape)
                        .background(SimplePopupConfig.backgroundColor())
                }
                .then(modifier),
        ) {
            content()
        }
    }
}

@Composable
fun SimplePopupItem(
    modifier: Modifier = Modifier,
    value: TextValue,
    icon: IconValue? = null,
    onClick: () -> Unit,
) {
    DefaultSelectorItem(
        modifier = modifier
            .fillMaxWidth()
            .doOnClick(onClick = onClick)
            .padding(horizontal = SimpleSelectorConfig.Padding, vertical = SimpleSelectorConfig.Padding / 2),
        value = value,
        icon = icon
    )
}