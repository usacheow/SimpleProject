package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.ui.graphics.Color
import com.usacheow.corecommon.container.textValue

class CardNumberFormatter : SimpleFormatter {

    private val pattern = listOf(
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider("-"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider("-"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider("-"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
    )

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