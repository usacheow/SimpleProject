package com.usacheow.coreuicompose.uikit.other

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun FadeText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 1,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    style: TextStyle = LocalTextStyle.current,
) {
    var textCountInLine by remember { mutableStateOf(text.length) }

    Text(
        text = getAnnotatedString(text, textCountInLine, color),
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        maxLines = maxLines,
        inlineContent = inlineContent,
        overflow = TextOverflow.Ellipsis,
        style = style,
        modifier = modifier,
        onTextLayout = {
            if (maxLines != Int.MAX_VALUE) textCountInLine = it.getLineEnd(maxLines - 1, true)
        }
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun createFadeAnnotatedString(text: String, color: Color): AnnotatedString = buildAnnotatedString {

    val brush = Brush.horizontalGradient(
        0.9f to color.copy(alpha = 1.0f),
        0.8f to color.copy(alpha = 0.8f),
        1.0f to color.copy(alpha = 0.0f),
    )

    append(text)
    addStyle(
        style = SpanStyle(brush = brush),
        start = text.lastIndex - 2,
        end = text.lastIndex + 1,
    )
}

@Composable
private fun getAnnotatedString(
    text: AnnotatedString,
    textCountInLine: Int,
    color: Color,
): AnnotatedString {
    return if (text.length > textCountInLine) {
        createFadeAnnotatedString(text.substring(0, textCountInLine), color)
    } else {
        text
    }
}