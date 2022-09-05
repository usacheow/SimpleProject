package com.usacheow.coreuicompose.uikit.status

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineS
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun SimpleAlertDialogUi(
    text: TextValue,
    buttonText: TextValue,
    onClickRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClickRequest,
        text = {
            Text(
                text = text.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.bodyLarge,
                color = AppTheme.specificColorScheme.symbolPrimary
            )
        },
        dismissButton = {
            SimpleButtonInlineS(onClick = onClickRequest) {
                SimpleButtonContent(buttonText)
            }
        },
        confirmButton = {},
    )
}