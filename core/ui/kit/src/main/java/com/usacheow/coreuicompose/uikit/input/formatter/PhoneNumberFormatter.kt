package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.ui.graphics.Color
import com.usacheow.corecommon.container.textValue

private const val PhonePatterSymbol = '#'

class PhoneNumberFormatter(
    format: String = "+7(###)###-##-##",
) : SimpleFormatter {

    private val pattern = format.map {
        when (it) {
            PhonePatterSymbol -> SimpleInputSymbol.Inputted("0")
            else -> SimpleInputSymbol.Divider(it.toString())
        }
    }

    private val length = pattern.count { it is SimpleInputSymbol.Inputted }

    override fun placeholder() = pattern.joinToString("") { it.value }.textValue()

    override fun onValueChanged(action: (String) -> Unit) = { value: String ->
        action(value.filter { it.isDigit() }.take(length))
    }

    override fun visualTransformation(
        inputtedPartColor: Color,
        otherPartColor: Color,
    ) = SimpleVisualTransformation(
        pattern = pattern,
        inputtedPartColor = inputtedPartColor,
        otherPartColor = otherPartColor,
    )
}