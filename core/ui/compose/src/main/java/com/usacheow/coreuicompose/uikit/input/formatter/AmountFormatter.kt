package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.usacheow.corecommon.strings.RU_LOCALE
import com.usacheow.corecommon.container.textValue
import com.usacheow.corecommon.model.CurrencyType
import java.text.NumberFormat

class AmountFormatter(
    private val currencyType: CurrencyType
) : SimpleFormatter {

    private val formatter = NumberFormat.getNumberInstance(RU_LOCALE)
    private val numberFormatterSpace = ' '
    private val trueSpace = ' '
    private val amountDivider = ','
    private val fractionalPartMaxLength = 2

    override fun placeholder() = "".textValue()

    override fun onValueChanged(action: (String) -> Unit) = { value: String ->
        var newValue = value.replace('.', amountDivider)
            .filter { it.isDigit() || it == amountDivider }
        val firstDividerIndex = newValue.indexOfFirst { it == amountDivider }
        newValue = newValue.filterIndexed { index, c -> c != amountDivider || index == firstDividerIndex }
        action(newValue)
    }

    override fun visualTransformation(
        inputtedPartColor: Color,
        otherPartColor: Color,
    ) = object : VisualTransformation {

        private val suffix = " ${currencyType.symbol}"
        private var lastValue = ""

        private val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                var arg = 0
                var index = offset
                lastValue.forEach {
                    if (index == 0) return@forEach
                    if (it != trueSpace) index -= 1
                    else arg += 1
                }
                return offset + arg
            }

            override fun transformedToOriginal(offset: Int): Int {
                var arg = 0
                var index = offset
                lastValue.forEach {
                    if (index == 0) return@forEach
                    if (it == trueSpace) arg += 1
                    index -= 1
                }
                return offset - arg
            }
        }

        override fun filter(text: AnnotatedString): TransformedText {
            return TransformedText(format(text.text), offsetMapping)
        }

        private fun format(text: String): AnnotatedString {
            lastValue = format1(text)
            if (text.isEmpty()) return AnnotatedString(text)
            return buildAnnotatedString {
                append(lastValue)
                append(suffix)
                addStyle(SpanStyle(color = inputtedPartColor), 0, length - suffix.length)
                addStyle(SpanStyle(color = otherPartColor), length - suffix.length, length)
            }
        }

        private fun format1(value: String): String {
            return if (value.isEmpty()) {
                value
            } else {
                val parts = value.split(amountDivider)
                val wholePart = parts[0]
                val fractionalPart = parts.getOrNull(1)?.take(fractionalPartMaxLength)
                val parsedWholePart = formatter.parse(wholePart)
                if (parsedWholePart == 0) {
                    ""
                } else {
                    var formattedWholePart = formatter.format(parsedWholePart)
                    if (fractionalPart != null) {
                        formattedWholePart += "$amountDivider$fractionalPart"
                    }
                    formattedWholePart
                }
            }.replace(numberFormatterSpace, trueSpace)
        }
    }
}