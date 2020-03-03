package com.kapmayn.coreuikit.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.kapmayn.coreuikit.utils.textinput.DecimalFormatter
import com.kapmayn.coreuikit.utils.textinput.FormatterTextWatcher
import java.math.BigDecimal

fun EditText.onTextChanged(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action(s.toString())
        }
    })
}

fun EditText.afterTextChanged(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.clearFocusOnImeClick(actionId: Int, onClearedAction: () -> Unit = {}) {
    if (actionId == EditorInfo.IME_ACTION_DONE) {
        clearFocus()
        onClearedAction()
    }
}

fun EditText.showKeyboard() {
    post {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun TextInputEditText.addCurrencyFormatter(defaultHint: String? = null) {
    val formatter = DecimalFormatter().apply {
        negativePrefix = ""
        positivePrefix = ""
    }
    inputType = android.text.InputType.TYPE_CLASS_PHONE
    addTextChangedListener(FormatterTextWatcher(formatter))

    hint = if (defaultHint.isNullOrBlank()) {
        formatter.format(BigDecimal.ZERO)
    } else {
        formatter.format(defaultHint.toDoubleOrNull())
    }

    setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            setSelection(text?.length ?: 0)
        }
    }
}