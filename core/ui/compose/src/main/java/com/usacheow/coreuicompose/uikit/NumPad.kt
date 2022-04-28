package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen
import com.usacheow.corecommon.R as CoreCommonR

class NumPadAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
) {
    companion object {

        @Composable
        fun biometric(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.fingerprint, onClick)

        @Composable
        fun delete(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.delete, onClick)

        @Composable
        fun accept(onClick: () -> Unit) = NumPadAction(AppTheme.specificIcons.done, onClick)
    }
}

@Composable
fun NumPad(
    action: NumPadAction? = null,
    onForgetClick: () -> Unit,
    onNumberClick: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMarginHorizontal),
        horizontalArrangement = Arrangement.spacedBy(NumPadConfig.ButtonMarginHorizontal),
        modifier = Modifier
            .padding(NumPadConfig.ButtonMarginHorizontal)
            .wrapContentWidth(),
    ) {
        items(9) {
            NumberButton(text = (it + 1).toString(), onClick = onNumberClick)
        }
        item {
            ForgetButton(onClick = onForgetClick)
        }
        item {
            NumberButton(text = "0", onClick = onNumberClick)
        }
        item {
            action?.icon?.let {
                IconButton(
                    imageVector = it,
                    onClick = action.onClick,
                )
            }
        }
    }
}

@Composable
private fun NumberButton(text: String, onClick: (String) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = AppTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .background(color = AppTheme.specificColorScheme.surfaceVariant, shape = NumPadConfig.Shape)
                .clip(NumPadConfig.Shape)
                .clickable { onClick(text) }
                .wrapContentHeight(),
        )
    }
}

@Composable
private fun ForgetButton(onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(CoreCommonR.string.num_pad_forget),
            style = AppTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = AppTheme.specificColorScheme.symbolSecondary,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .clip(NumPadConfig.Shape)
                .clickable { onClick() }
                .padding(8.dp)
                .wrapContentHeight(),
        )
    }
}

@Composable
private fun IconButton(imageVector: ImageVector, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = AppTheme.specificColorScheme.symbolSecondary,
            modifier = Modifier
                .size(NumPadConfig.ButtonSize)
                .clip(NumPadConfig.Shape)
                .clickable { onClick() }
                .padding(24.dp),
        )
    }
}

private object NumPadConfig {

    val ButtonSize = 72.dp
    val ButtonMarginHorizontal = 20.dp
    val Shape = RoundedCornerShape(Dimen.radius_extra_large)
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SimplePreview {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NumPad(
                action = NumPadAction.accept {},
                onForgetClick = {},
                onNumberClick = { },
            )
        }
    }
}