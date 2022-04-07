package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.compose.ImageValue
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.get

@Composable
fun ButtonContent(
    text: TextValue,
    icon: ImageValue = ImageValue.Empty,
) {
    val textValue = text.get()
    val iconPaddingEnd = when {
        textValue.isEmpty() -> 0.dp
        else -> 8.dp
    }

    icon.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Button icon",
            tint = LocalContentColor.current,
            modifier = Modifier.padding(end = iconPaddingEnd),
        )
    }
    Text(
        text = textValue,
        color = LocalContentColor.current,
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SimpleButtonPreview() {
    SimplePreview {
        PreviewContentSimpleButton()
    }
}

@Composable
fun PreviewContentSimpleButton() {
    Column {
        Button(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { ButtonContent(TextValue.Simple("Simple button"), ImageValue.Empty) }
        Row {
            Button(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Simple("Simple button"), ImageValue.Empty) }
            Button(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add)) }
        }

        FilledTonalButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { ButtonContent(TextValue.Simple("Tonal button"), ImageValue.Empty) }
        Row {
            FilledTonalButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Simple("Tonal button"), ImageValue.Empty) }
            FilledTonalButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add)) }
        }

        ElevatedButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { ButtonContent(TextValue.Simple("Elevated button"), ImageValue.Empty) }
        Row {
            ElevatedButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Simple("Elevated button"), ImageValue.Empty) }
            ElevatedButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add)) }
        }
        OutlinedButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { ButtonContent(TextValue.Simple("Outlined button"), ImageValue.Empty) }
        Row {
            OutlinedButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Simple("Outlined button"), ImageValue.Empty) }
            OutlinedButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add)) }
        }

        TextButton(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { ButtonContent(TextValue.Simple("Text button"), ImageValue.Empty) }
        Row {
            TextButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Simple("Text button"), ImageValue.Empty) }
            TextButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
            ) { ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add)) }
        }
        IconButton(onClick = {}) {
            ButtonContent(TextValue.Empty, ImageValue.Vector(Icons.Default.Add))
        }
    }
}