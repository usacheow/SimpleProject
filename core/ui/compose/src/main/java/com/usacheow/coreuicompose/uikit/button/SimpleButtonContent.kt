package com.usacheow.coreuicompose.uikit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.PreviewAppTheme

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
            modifier = Modifier.size(getContentHeight()),
        )
    }
    text?.get()?.let {
        Text(
            text = it,
            color = LocalContentColor.current,
            style = LocalTextStyle.current,
            modifier = Modifier
                .padding(start = textPaddingStart, end = textPaddingEnd)
                .height(getContentHeight()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
    iconRight?.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Button icon",
            tint = LocalContentColor.current,
            modifier = Modifier.size(getContentHeight()),
        )
    }
}

@Composable
fun SimpleButtonContentLoader() {
    CircularProgressIndicator(
        color = LocalContentColor.current,
        modifier = Modifier.size(getContentHeight()),
    )
}

@Composable
private fun getContentHeight() = LocalTextStyle.current.lineHeight.value.dp + 3.5.dp

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        Row {
            SimpleButtonTonalL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContent("Simple button".textValue()) }

            SimpleButtonTonalL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContent(null, iconLeft = AppTheme.specificIcons.add) }

            SimpleButtonTonalL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContentLoader() }
        }
    }
}