package com.usacheow.coreuiview.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.databinding.ViewMessageBannerBinding
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.makeGone
import com.usacheow.coreuiview.tools.makeVisible
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.tools.resource.string
import com.usacheow.coreuiview.uikit.molecule.ListTileItem
import com.usacheow.coreuitheme.R as CoreUiThemeR

class MessageBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding by lazy { ViewMessageBannerBinding.inflate(LayoutInflater.from(context), this, true) }

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
) {

    companion object {

        fun reloadTileItem(
            message: TextSource,
            description: TextSource?,
            clickListener: () -> Unit,
        ) = ListTileItem(
            value = message,
            bottomDescription = description,
            rightImageInfo = ImageSource.Res(CoreUiThemeR.drawable.ic_refresh),
            clickListener = clickListener,
        )
    }
}