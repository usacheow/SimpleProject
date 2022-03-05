package com.usacheow.coreuiview.tools.textinput

import android.text.Editable

class FormatterTextWatcher(
    private val formatter: IFormatter
) : EmptyTextWatcher() {

    private var beforeText: String? = null
    private var startPosition = 0

    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
        super.beforeTextChanged(text, start, count, after)
        beforeText = text.toString()
        startPosition = start
    }

    override fun afterTextChanged(editable: Editable) {
        super.afterTextChanged(editable)
        editable.apply {
            if (isEmpty() || toString() == beforeText) return

            val filtered = formatter.formatText(toString())
            if (filtered.isEmpty()) {
                replace(0, length, beforeText)
            } else if (filtered != toString()) {
                replace(0, length, filtered)
            }
        }
    }

    fun initialFormat(editable: Editable) {
        editable.apply {
            if (isEmpty()) return

            val formatted = formatter.formatText(toString())
            if (toString() != formatted) {
                replace(0, length, formatted)
            }
        }
    }
}

interface IFormatter {

    fun formatText(cleanText: String): String

    fun cleanText(formattedText: String): String
}