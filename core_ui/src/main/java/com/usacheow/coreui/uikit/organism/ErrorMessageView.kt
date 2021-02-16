package com.usacheow.coreui.uikit.organism

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

    fun populate(state: ErrorMessageItem) {
        binding.errorMessageTitleView.text = state.title
        binding.errorMessageDescriptionView.text = state.description

        state.repeatClickAction?.let {
            binding.repeatButton.makeVisible()
            binding.repeatButton.doOnClick { it() }
        } ?: binding.repeatButton.makeGone()
    }
}

data class ErrorMessageItem(
    val title: String?,
    val description: String?,
    val repeatClickAction: (() -> Unit)?,
)

fun ErrorMessageView.showOrHideError(state: ErrorMessageItem?) {
    state?.let {
        makeVisible()
        populate(state)
    } ?: makeGone()
}