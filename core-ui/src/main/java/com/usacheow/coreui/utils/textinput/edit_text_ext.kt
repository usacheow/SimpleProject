package com.usacheow.coreui.utils.textinput

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal

fun EditText.onTextChanged(action: (String) -> Unit) = addTextChangedListener(object : EmptyTextWatcher() {
    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        action(text.toString())
    }
})

fun EditText.afterTextChanged(action: (String) -> Unit) = addTextChangedListener(object : EmptyTextWatcher() {
    override fun afterTextChanged(editable: Editable) {
        action(editable.toString())
    }
})

fun EditText.doOnActionClick(imeActionId: Int, action: () -> Unit = {}) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
        }
        actionId != EditorInfo.IME_ACTION_DONE
    }
}

fun TextInputEditText.addCurrencyFormatter(defaultHint: String? = null): TextWatcher {
    val formatter = DecimalFormatter().apply {
        negativePrefix = ""
        positivePrefix = ""
    }
    val textWatcher = FormatterTextWatcher(formatter)
    inputType = android.text.InputType.TYPE_CLASS_PHONE
    addTextChangedListener(textWatcher)

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

    return textWatcher
}

fun TextInputEditText.addPhoneNumberFormatter(
    onPhoneNumberCompleted: (String) -> Unit = {},
    onPhoneNumberInputChanged: (String) -> Unit = {},
) = PhoneNumberFormatter(
    inputEditText = this,
    onPhoneNumberCompleted = onPhoneNumberCompleted,
    onPhoneNumberInputChanged = onPhoneNumberInputChanged
)