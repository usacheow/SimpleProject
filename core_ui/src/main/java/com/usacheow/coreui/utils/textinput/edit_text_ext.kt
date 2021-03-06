package com.usacheow.coreui.utils.textinput

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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

fun EditText.showKeyboard() {
    post {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideKeyboard() {
    val inputMethodManger = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManger!!.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.isKeyboardEnabled(): Boolean {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return imm.enabledInputMethodList.any { it.packageName == packageName }
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