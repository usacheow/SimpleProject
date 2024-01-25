package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class SimpleVisualTransformation(
    private val pattern: List<SimpleInputSymbol>,
    private val inputtedPartColor: Color,
    private val otherPartColor: Color,
) : VisualTransformation {

    private val offsetMapping = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int {
            var finalOffset = offset
            var index = 0

            pattern.forEach {
                if (offset <= index) return@forEach
                when (it) {
                    is SimpleInputSymbol.Inputted -> index += 1
                    is SimpleInputSymbol.Divider -> finalOffset += it.value.length
                }
            }
            return finalOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var finalOffset = offset
            var index = 0

            pattern.forEach {
                if (offset <= index) return@forEach
                if (it is SimpleInputSymbol.Divider) finalOffset -= it.value.length
                index += 1
            }
            return finalOffset
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(format(text.text), offsetMapping)
    }

    private fun format(text: String): AnnotatedString {
        var index = 0
        var patternIndex = 0
        return buildAnnotatedString {
            pattern.forEach {
                when {
                    text.length <= index -> append(it.value)
                    else -> {
                        patternIndex += it.value.length
                        when (it) {
                            is SimpleInputSymbol.Inputted -> append(text[index]).also { index += 1 }
                            is SimpleInputSymbol.Divider -> append(it.value)
                        }
                    }
                }
            }
            addStyle(SpanStyle(color = inputtedPartColor), 0, patternIndex)
            addStyle(SpanStyle(color = otherPartColor), patternIndex, length)
        }
    }
}

sealed class SimpleInputSymbol(val value: String) {
    class Inputted(value: String) : SimpleInputSymbol(value)
    class Divider(value: String) : SimpleInputSymbol(value)
}