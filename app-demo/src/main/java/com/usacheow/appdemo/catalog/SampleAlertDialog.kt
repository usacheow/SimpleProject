package com.usacheow.appdemo.catalog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.uikit.SimpleButtonContent

@Composable
fun SampleAlertDialog(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = { Text("Material dialog") },
        text = { Text("Material dialog example") },
        confirmButton = {
            TextButton(onClick = onDismissRequest) { SimpleButtonContent(TextValue.Simple("Agree")) }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) { SimpleButtonContent(TextValue.Simple("Disagree")) }
        },
        onDismissRequest = onDismissRequest,
    )
}