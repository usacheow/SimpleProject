package com.usacheow.coreuiview.tools.textinput

import android.text.Editable
import android.text.TextWatcher

open class EmptyTextWatcher : TextWatcher {

    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editable: Editable) = Unit
}