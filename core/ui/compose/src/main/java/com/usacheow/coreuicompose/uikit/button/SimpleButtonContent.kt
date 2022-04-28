package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.get

@Composable
fun SimpleButtonContent(
    text: TextValue?,
    iconLeft: IconValue? = null,
    iconRight: IconValue? = null,
) {
    val textPaddingStart = when (iconLeft) {
        null -> 4.dp
        else -> 8.dp
    }
    val textPaddingEnd = when (iconRight) {
        null -> 4.dp
        else -> 8.dp
    }

    iconLeft?.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Button icon",
            tint = LocalContentColor.current,
            modifier = Modifier.size(LocalTextStyle.current.lineHeight.value.dp),
        )
    }
    text?.get()?.let {
        Text(
            text = it,
            color = LocalContentColor.current,
            modifier = Modifier.padding(start = textPaddingStart, end = textPaddingEnd),
        )
    }
    iconRight?.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Button icon",
            tint = LocalContentColor.current,
            modifier = Modifier.size(LocalTextStyle.current.lineHeight.value.dp),
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SimplePreview {
        Button(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {},
        ) { SimpleButtonContent(TextValue.Simple("Simple button")) }
    }
}