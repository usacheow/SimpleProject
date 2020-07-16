package com.usacheow.coreui.uikit.information

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.utils.ext.doOnClick
import com.usacheow.coreui.utils.ext.makeGone
import com.usacheow.coreui.utils.ext.makeVisible
import kotlinx.android.synthetic.main.view_error_message.view.errorMessageView
import kotlinx.android.synthetic.main.view_error_message.view.repeatButton

class ErrorMessageView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_error_message, this)
    }

    fun populate(errorText: String, repeatClickAction: (() -> Unit)?) {
        errorMessageView.text = errorText

        if (repeatClickAction == null) {
            repeatButton.makeGone()
        } else {
            repeatButton.makeVisible()
            repeatButton.doOnClick { repeatClickAction() }
        }
    }
}

fun ErrorMessageView.showOrHideError(errorText: String?, repeatClickAction: (() -> Unit)?) {
    errorText?.let {
        makeVisible()
        populate(errorText, repeatClickAction)
    } ?: makeGone()
}