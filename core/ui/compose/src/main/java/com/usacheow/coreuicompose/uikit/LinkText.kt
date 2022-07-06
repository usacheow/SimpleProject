package com.usacheow.coreuicompose.uikit

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.usacheow.coreuitheme.compose.AppTheme

data class LinkTextData(
    val text: String,
    val onClick: () -> Unit,
)

@Composable
fun LinkText(
    text: String,
    style: TextStyle = AppTheme.typography.bodyMedium,
    color: Color = AppTheme.specificColorScheme.symbolPrimary,
    linkTextData: List<LinkTextData>,
    modifier: Modifier = Modifier,
) {
    val annotatedString = createAnnotatedString(text, linkTextData)

    ClickableText(
        text = annotatedString,
        style = style.copy(color = color),
        modifier = modifier,
        onClick = { offset ->
            linkTextData.forEach { item ->
                annotatedString
                    .getStringAnnotations(item.text, offset, offset)
                    .firstOrNull()
                    ?.let { item.onClick() }
            }
        },
    )
}

@Composable
private fun createAnnotatedString(text: String, data: List<LinkTextData>): AnnotatedString {
    return buildAnnotatedString {
        append(text)
        data.forEach {
            val startIndex = text.indexOf(it.text)
            val endIndex = startIndex + it.text.length
            addStyle(
                style = SpanStyle(color = AppTheme.specificColorScheme.primary, textDecoration = TextDecoration.None),
                start = startIndex,
                end = endIndex,
            )
            addStringAnnotation(
                tag = it.text,
                annotation = it.text,
                start = startIndex,
                end = endIndex
            )
        }
    }
}