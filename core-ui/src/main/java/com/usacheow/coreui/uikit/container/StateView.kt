package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.usacheow.coreui.databinding.ViewStateBinding
import com.usacheow.coreui.uikit.helper.makeGone
import com.usacheow.coreui.uikit.helper.makeVisible
import com.usacheow.coreui.uikit.organism.MessageBannerItem

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding by lazy { ViewStateBinding.bind(this) }

    fun setHiddenState() = with(binding) {
        messageView.makeGone()
        loaderView.makeGone()
    }

    fun setLoadState() = with(binding) {
        messageView.makeGone()
        loaderView.makeVisible()
    }

    fun setErrorState(message: MessageBannerItem) = with(binding) {
        messageView.populate(message)

        messageView.makeVisible()
        loaderView.makeGone()
    }

    fun setEmptyState(message: MessageBannerItem) = with(binding) {
        messageView.populate(message)

        messageView.makeVisible()
        loaderView.makeGone()
    }
}