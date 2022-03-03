package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuicompose.tools.SimplePreview

private val buttonSize = 56.dp
private val buttonMarginHorizontal = 12.dp

enum class NumPadActionMode(val icon: ImageVector?) {
    Biometric(Icons.Default.Fingerprint),
    Next(Icons.Default.NavigateNext),
    Accept(Icons.Default.Done),
    Empty(null),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumPad(
    mode: NumPadActionMode,
    onBackspaceClick: () -> Unit,
    onActionClick: () -> Unit,
    onNumberClick: (String) -> Unit,
) {
    LazyVerticalGrid(cells = GridCells.Fixed(3)) {
        items(9) {
            NumPadButton(text = (it + 1).toString(), onClick = onNumberClick)
        }
        item {
            NumPadButton(
                imageVector = Icons.Default.KeyboardBackspace,
                contentDescription = "Backspace button",
                onClick = onBackspaceClick,
            )
        }
        item {
            NumPadButton(text = "0", onClick = onNumberClick)
        }
        item {
            mode.icon?.let {
                NumPadButton(
                    imageVector = it,
                    contentDescription = "Action button",
                    onClick = onActionClick,
                )
            }
        }
    }
}

@Composable
private fun NumPadButton(text: String, onClick: (String) -> Unit) {
    Text(
        text = text,
        style = AppTheme.typography.displaySmall,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = buttonMarginHorizontal)
            .clickable { onClick(text) }
            .size(buttonSize),
    )
}

@Composable
private fun NumPadButton(imageVector: ImageVector, contentDescription: String, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(buttonSize)
            .padding(horizontal = buttonMarginHorizontal),
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(buttonSize)
                .padding(horizontal = buttonMarginHorizontal),
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NumPadPreview() {
    val enteredCode = remember { mutableStateOf("7") }

    SimplePreview {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(2.dp, AppTheme.commonColors.outline),
                        shape = AppTheme.shapes.small,
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = enteredCode.value,
                    style = AppTheme.typography.displayLarge,
                )
            }
            NumPad(
                mode = NumPadActionMode.Accept,
                onBackspaceClick = { enteredCode.value = enteredCode.value.dropLast(1) },
                onActionClick = {},
                onNumberClick = { enteredCode.value += it },
            )
        }
    }
}