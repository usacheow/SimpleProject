package com.usacheow.coreui.uikit.information

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.usacheow.coreui.databinding.ViewErrorMessageBinding
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible

class ErrorMessageView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy { ViewErrorMessageBinding.inflate(LayoutInflater.from(context), this, true) }

    fun populate(errorText: String, repeatClickAction: (() -> Unit)?) {
        binding.errorMessageView.text = errorText

        if (repeatClickAction == null) {
            binding.repeatButton.makeGone()
        } else {
            binding.repeatButton.makeVisible()
            binding.repeatButton.doOnClick { repeatClickAction() }
        }
    }
}

fun ErrorMessageView.showOrHideError(errorText: String?, repeatClickAction: (() -> Unit)?) {
    errorText?.let {
        makeVisible()
        populate(errorText, repeatClickAction)
    } ?: makeGone()
}