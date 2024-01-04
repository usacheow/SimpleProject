package com.usacheow.coreuicompose.uikit.status

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.button.SimpleButton
import com.usacheow.coreuicompose.uikit.button.SimpleButtonColors
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonSize
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun SimpleConfirmDialogUi(
    title: TextValue,
    text: TextValue,
    confirmButtonText: TextValue,
    dismissButtonText: TextValue,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.titleLarge,
                color = AppTheme.specificColorScheme.symbolPrimary,
            )
        },
        text = {
            Text(
                text = text.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.bodyMedium,
                color = AppTheme.specificColorScheme.symbolPrimary,
            )
        },
        confirmButton = {
            SimpleButton(
                size = SimpleButtonSize.m(),
                colors = SimpleButtonColors.inline(),
                onClick = onConfirmRequest,
            ) {
                SimpleButtonContent(confirmButtonText)
            }
        },
        dismissButton = {
            SimpleButton(
                size = SimpleButtonSize.m(),
                colors = SimpleButtonColors.inline(),
                onClick = onDismissRequest,
            ) {
                SimpleButtonContent(dismissButtonText)
            }
        },
    )
}