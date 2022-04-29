package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.runtime.Composable
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuitheme.compose.AppTheme

object PhoneNumberFormatter {

    private val pattern = listOf(
        SimpleInputSymbol.Divider("+"),
        SimpleInputSymbol.Inputted("7"),
        SimpleInputSymbol.Divider(" ("),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider(") "),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider("-"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Divider("-"),
        SimpleInputSymbol.Inputted("0"),
        SimpleInputSymbol.Inputted("0"),
    )

    private val cardNumberLength = pattern.count { it is SimpleInputSymbol.Inputted }

    fun placeholder() = TextValue.Simple(pattern.joinToString("") { it.value })

    fun onValueChanged(action: (String) -> Unit) = { value: String ->
        action(value.filter { it.isDigit() }.take(cardNumberLength))
    }

    @Composable
    fun visualTransformation() = SimpleVisualTransformation(
        pattern = pattern,
        inputtedPartColor = AppTheme.specificColorScheme.symbolPrimary,
        otherPartColor = AppTheme.specificColorScheme.symbolSecondary,
    )
}