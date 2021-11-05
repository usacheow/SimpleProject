package com.usacheow.coreui.compose.tools

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kotlin.math.min

fun String.parseHtml(): AnnotatedString {
    fun recurse(string: String, to: AnnotatedString.Builder) {
        fun tagToStyle(tag: String): SpanStyle {
            return when (tag) {
                "<b>" -> SpanStyle(fontWeight = FontWeight.Bold)
                "<i>" -> SpanStyle(fontStyle = FontStyle.Italic)
                "<u>" -> SpanStyle(textDecoration = TextDecoration.Underline)
                else -> SpanStyle(fontWeight = FontWeight.Normal)
            }
        }

        val tags = linkedMapOf(
            "<b>" to "</b>",
            "<i>" to "</i>",
            "<u>" to "</u>",
        )
        val startTag = tags.keys.find { string.startsWith(it) }
        val endTag = tags.values.find { string.startsWith(it) }

        when {
            tags.any { string.startsWith(it.value) } -> {
                to.pop()
                recurse(string.removeRange(0, endTag!!.length), to)
            }

            tags.any { string.startsWith(it.key) } -> {
                to.pushStyle(tagToStyle(startTag!!))
                recurse(string.removeRange(0, startTag.length), to)
            }

            tags.any { string.contains(it.key) || string.contains(it.value) } -> {
                val firstStart = tags.keys.map { string.indexOf(it) }
                    .filterNot { it == -1 }
                    .minOrNull() ?: -1
                val firstEnd = tags.values.map { string.indexOf(it) }
                    .filterNot { it == -1 }
                    .minOrNull() ?: -1
                val first = when {
                    firstStart == -1 -> firstEnd
                    firstEnd == -1 -> firstStart
                    else -> min(firstStart, firstEnd)
                }

                to.append(string.substring(0, first))

                recurse(string.removeRange(0, first), to)
            }

            else -> {
                to.append(string)
            }
        }
    }

    val newlineReplace = this.replace("<br>", "\n")

    return buildAnnotatedString {
        recurse(newlineReplace, this)
    }
}