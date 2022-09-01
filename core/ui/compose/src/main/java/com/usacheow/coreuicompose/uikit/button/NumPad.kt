package com.usacheow.coreuicompose.uikit.button

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.strings.StringKey
import com.usacheow.coreuicompose.tools.defaultBorder
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.tools.longPress
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.LocalStringHolder
import com.usacheow.coreuitheme.compose.PreviewAppTheme

class NumPadAction(
    val icon: IconValue,
    val onClick: () -> Unit,
) {
    companion object {

        fun biometric(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.fingerprint, onClick)

        fun delete(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.delete, onClick)

        fun accept(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.done, onClick)
    }
}

@Composable
fun NumPad(
    action: NumPadAction? = null,
    onForgetClick: (() -> Unit)?,
    onNumberClick: (Int) -> Unit,
) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> LandscapeNumPad(
            action = action,
            onForgetClick = onForgetClick,
            onNumberClick = onNumberClick,
        )
        else -> PortraitNumPad(
            action = action,
            onForgetClick = onForgetClick,
            onNumberClick = onNumberClick,
        )
    }
}

@Composable
fun PortraitNumPad(
    action: NumPadAction? = null,
    onForgetClick: (() -> Unit)?,
    onNumberClick: (Int) -> Unit,
) {
    val hapticFeedback = LocalHapticFeedback.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMargin),
            horizontalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMargin),
            modifier = Modifier
                .padding(NumPadConfig.ButtonMargin)
                .width(NumPadConfig.ButtonSize * 3 + NumPadConfig.ButtonMargin * 2),
            userScrollEnabled = false,
        ) {
            numPadContent(
                hapticFeedback = hapticFeedback,
                isPortraitOrientation = true,
                action = action,
                onForgetClick = onForgetClick,
                onNumberClick = onNumberClick,
            )
        }
    }
}

@Composable
fun LandscapeNumPad(
    action: NumPadAction? = null,
    onForgetClick: (() -> Unit)?,
    onNumberClick: (Int) -> Unit,
) {
    val hapticFeedback = LocalHapticFeedback.current
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMargin),
        horizontalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMargin),
        modifier = Modifier
            .padding(NumPadConfig.ButtonMargin)
            .width(NumPadConfig.ButtonSize * 4 + NumPadConfig.ButtonMargin * 3),
        userScrollEnabled = false,
    ) {
        numPadContent(
            hapticFeedback = hapticFeedback,
            isPortraitOrientation = false,
            action = action,
            onForgetClick = onForgetClick,
            onNumberClick = onNumberClick,
        )
    }
}

private fun LazyGridScope.numPadContent(
    hapticFeedback: HapticFeedback,
    isPortraitOrientation: Boolean,
    action: NumPadAction? = null,
    onForgetClick: (() -> Unit)?,
    onNumberClick: (Int) -> Unit,
) {
    val buttons = when {
        isPortraitOrientation -> listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, Helper.Forget, 0, Helper.Action)
        else -> listOf(1, 4, 7, 2, 5, 8, 3, 6, 9, Helper.Action, 0, Helper.Forget)
    }
    items(buttons) {
        when (it) {
            is Int -> NumberButton(number = it) { value ->
                onNumberClick(value)
                hapticFeedback.longPress()
            }
            Helper.Action -> action?.icon?.let {
                IconButton(icon = it) {
                    action.onClick()
                    hapticFeedback.longPress()
                }
            } ?: Box(Modifier)
            Helper.Forget -> onForgetClick?.let {
                ForgetButton {
                    onForgetClick()
                    hapticFeedback.longPress()
                }
            } ?: Box(Modifier)
        }
    }
}

private sealed class Helper {
    object Action : Helper()
    object Forget : Helper()
}

@Composable
private fun NumberButton(number: Int, onClick: (Int) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = number.toString(),
            style = AppTheme.specificTypography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .defaultBorder(NumPadConfig.shape())
                .clickable { onClick(number) }
                .wrapContentHeight(),
        )
    }
}

@Composable
private fun ForgetButton(onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = LocalStringHolder.current(StringKey.NumPadForget),
            style = AppTheme.specificTypography.bodySmall,
            textAlign = TextAlign.Center,
            color = AppTheme.specificColorScheme.symbolSecondary,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .clip(NumPadConfig.shape())
                .clickable { onClick() }
                .padding(8.dp)
                .wrapContentHeight(),
        )
    }
}

@Composable
private fun IconButton(icon: IconValue, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Icon(
            painter = icon.get(),
            contentDescription = null,
            tint = AppTheme.specificColorScheme.symbolSecondary,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .clip(NumPadConfig.shape())
                .clickable { onClick() }
                .padding(24.dp),
        )
    }
}

object NumPadConfig {

    val ButtonSize = 72.dp
    val ButtonMargin = 20.dp
    val ContainerWidth = ButtonSize * 3 + ButtonMargin * 2

    @Composable
    fun shape() = AppTheme.shapes.extraLarge
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NumPad(
                action = NumPadAction.accept {},
                onForgetClick = {},
                onNumberClick = { },
            )
        }
    }
}