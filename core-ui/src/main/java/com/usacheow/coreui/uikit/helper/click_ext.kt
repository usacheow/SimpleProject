package com.usacheow.coreui.uikit.helper

import android.os.SystemClock
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/*Set ?selectableItemBackground programmatically

with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}
* */

fun View.doOnImmediateClick(action: (() -> Unit)?) {
    isEnabled = if (action == null) {
        setOnClickListener(null)
        false
    } else {
        setOnClickListener(DebounceOnClickListener(needDebounce = false, action))
        true
    }
}

fun View.doOnClick(action: (() -> Unit)?) {
    isEnabled = if (action == null) {
        setOnClickListener(null)
        false
    } else {
        setOnClickListener(DebounceOnClickListener(needDebounce = true, action))
        true
    }
}

fun EditText.doOnActionClick(imeActionId: Int, action: () -> Unit = {}) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
        }
        actionId != EditorInfo.IME_ACTION_DONE
    }
}

private class DebounceOnClickListener(
    private val needDebounce: Boolean = true,
    private val action: () -> Unit,
) : View.OnClickListener {

    private val DEBOUNCE_DELAY = 500
    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        if (needDebounce) {
            debounceClick()
        } else {
            simpleClick()
        }
    }

    private fun simpleClick() {
        action()
    }

    private fun debounceClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < DEBOUNCE_DELAY) {
            return
        } else {
            action()
        }

        lastClickTime = SystemClock.elapsedRealtime()
    }
}