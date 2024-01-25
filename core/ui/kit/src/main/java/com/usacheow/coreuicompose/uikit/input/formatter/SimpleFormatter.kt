package com.usacheow.coreuicompose.uikit.input.formatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue

interface SimpleFormatter {

    companion object {

        val Default = DefaultFormatter()
    }

    fun placeholder(): TextValue

    fun onValueChanged(action: (String) -> Unit): (String) -> Unit

    fun visualTransformation(
        inputtedPartColor: Color,
        otherPartColor: Color,
    ): VisualTransformation

    fun format(text: String) = visualTransformation(Color.Black, Color.Black)
        .filter(AnnotatedString(text))
        .text
        .text
}

class DefaultFormatter : SimpleFormatter {

    override fun placeholder() = "".textValue()

    override fun onValueChanged(action: (String) -> Unit) = action

    override fun visualTransformation(
        inputtedPartColor: Color,
        otherPartColor: Color,
    ): VisualTransformation = VisualTransformation.None
}