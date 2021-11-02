package com.usacheow.coreui.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.databinding.ViewErrorMessageBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.makeGone
import com.usacheow.coreui.uikit.helper.makeVisible
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.uikit.helper.string

class MessageBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding by lazy { ViewErrorMessageBinding.inflate(LayoutInflater.from(context), this, true) }

    fun populate(state: MessageBannerItem) {
        binding.iconView.populate(state.icon)
        binding.titleView.populate(state.title)
        binding.descriptionView.populate(state.description)

        state.button?.let {
            binding.repeatButton.text = string(it.res)
        }
        state.clickListener?.let {
            binding.repeatButton.makeVisible()
            binding.repeatButton.doOnClick { it() }
        } ?: binding.repeatButton.makeGone()
    }

    fun showOrHideError(state: MessageBannerItem?) {
        state?.let {
            makeVisible()
            populate(state)
        } ?: makeGone()
    }
}

data class MessageBannerItem(
    val icon: ImageSource? = null,
    val title: TextSource?,
    val description: TextSource?,
    val button: TextSource.Res? = null,
    val clickListener: (() -> Unit)? = null,
)