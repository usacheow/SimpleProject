package com.kapmayn.coreuikit.utils.textinput

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

private const val FRACTION_MIN = 0
private const val FRACTION_MAX = 2
private const val GROUP_SIZE = 3
private const val SEPARATOR_DECIMAL = '.'
private const val SEPARATOR_GROUP = ' '
private const val ZERO = '0'
private const val TWO_CHARACTERS = 2

class DecimalFormatter : DecimalFormat(), IFormatter {

    init {
        maximumFractionDigits = FRACTION_MAX
        minimumFractionDigits = FRACTION_MIN
        isGroupingUsed = true
        groupingSize = GROUP_SIZE
        decimalFormatSymbols = DecimalFormatSymbols().apply {
            decimalSeparator = SEPARATOR_DECIMAL
            groupingSeparator = SEPARATOR_GROUP
        }
        roundingMode = RoundingMode.DOWN
    }

    override fun formatText(cleanText: String) = cleanText(cleanText)

    override fun cleanText(formattedText: String): String {
        val decimal = formattedText.parseBigDecimal() ?: return ""

        val formattedText = format(decimal)
        return StringBuilder(formattedText).apply {
            // When decimal ends with '.' or '.0' or '.x0' symbols, DecimalFormat.format() cuts them off.
            // We don't want this, so if the original text contains them we revert the formatting.
            if (endsWithSeparator(formattedText)) {
                append(SEPARATOR_DECIMAL)
            }

            if (endsWithOneTrailingZero(formattedText)) {
                append(SEPARATOR_DECIMAL).append(ZERO)
            }

            if (endsWithTrailingZero(formattedText, formattedText)) {
                append(ZERO)
            }
        }.toString()
    }

    private fun endsWithSeparator(text: String): Boolean {
        return with(text) {
            if (isEmpty()) {
                false
            } else {
                SEPARATOR_DECIMAL == toCharArray()[length - 1]
            }
        }
    }

    private fun endsWithOneTrailingZero(text: String): Boolean {
        return with(text) {
            if (length > TWO_CHARACTERS) {
                val endsWithZero = ZERO == toCharArray()[length - 1]
                val endsWithOneTrailingZero = SEPARATOR_DECIMAL == toCharArray()[length - 2]
                endsWithZero && endsWithOneTrailingZero
            } else {
                false
            }
        }
    }

    private fun endsWithTrailingZero(text: String, formattedText: String): Boolean {
        with(text) {
            if (contains(SEPARATOR_DECIMAL) && formattedText.contains(SEPARATOR_DECIMAL)) {
                val secondText = split(SEPARATOR_DECIMAL)[1]
                if (secondText.length > 1 && secondText.toCharArray()[1] == ZERO) {
                    return true
                }
            }
        }
        return false
    }

    fun String.parseBigDecimal(): BigDecimal? {
        var value = this
        var result: BigDecimal? = null
        try {
            value = value.normalizeAmountValue("")
            value = value.removeNotDigits()!!
            result = BigDecimal(value)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun String?.normalizeAmountValue(fallback: String): String {
        return if (!this.isNullOrEmpty()) {
            this.trim().removeSpaces()!!.replace(',', '.')
        } else {
            fallback
        }
    }

    fun String?.removeSpaces(): String? = this?.replace(Regex("\\s"), "") ?: this

    fun String?.removeNotDigits(): String? = this?.replace(Regex("[^+0-9.-]"), "") ?: this
}