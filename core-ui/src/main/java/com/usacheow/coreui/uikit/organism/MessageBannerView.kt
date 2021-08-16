package com.usacheow.coreui.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.usacheow.coreui.databinding.ViewErrorMessageBinding
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible
import com.usacheow.coreui.utils.view.string

class MessageBannerView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

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