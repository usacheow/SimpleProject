package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.runtime.Composable
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuitheme.compose.AppTheme

private const val PhonePatterSymbol = '#'

class PhoneNumberFormatter(format: String = "+7(###)###-##-##") {

    private val pattern = format.map {
        when (it) {
            PhonePatterSymbol -> SimpleInputSymbol.Inputted("0")
            else -> SimpleInputSymbol.Divider(it.toString())
        }
    }

    val length = pattern.count { it is SimpleInputSymbol.Inputted }

    fun placeholder() = pattern.joinToString("") { it.value }.textValue()

    fun onValueChanged(action: (String) -> Unit) = { value: String ->
        action(value.filter { it.isDigit() }.take(length))
    }

    @Composable
    fun visualTransformation() = SimpleVisualTransformation(
        pattern = pattern,
        inputtedPartColor = AppTheme.specificColorScheme.symbolPrimary,
        otherPartColor = AppTheme.specificColorScheme.symbolSecondary,
    )
}