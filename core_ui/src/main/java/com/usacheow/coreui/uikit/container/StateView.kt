package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.usacheow.coreui.databinding.ViewListWithStatesViewBinding
import com.usacheow.coreui.uikit.organism.MessageBannerItem
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible

class StateView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy { ViewListWithStatesViewBinding.bind(this) }

    fun setHiddenState() = with (binding) {
        errorMessageView.makeGone()
        emptyMessageView.makeGone()
        loaderView.root.makeGone()
    }

    fun setLoadState() = with (binding) {
        errorMessageView.makeGone()
        emptyMessageView.makeGone()
        loaderView.root.makeVisible()
    }

    fun setErrorState(message: MessageBannerItem) = with (binding) {
        errorMessageView.populate(message)

        errorMessageView.makeVisible()
        emptyMessageView.makeGone()
        loaderView.root.makeGone()
    }

    fun setEmptyState(message: MessageBannerItem) = with (binding) {
        emptyMessageView.populate(message)

        errorMessageView.makeGone()
        emptyMessageView.makeVisible()
        loaderView.root.makeGone()
    }
}