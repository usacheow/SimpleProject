package com.usacheow.coreui.uikit.header

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewHeaderWithActionItemBinding
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.populate

class HeaderWithActionView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr), Populatable<HeaderWithActionItem> {

    private val binding by lazy { ViewHeaderWithActionItemBinding.bind(this) }

    override fun populate(model: HeaderWithActionItem) {
        binding.headerView.text = model.title
        binding.actionButton.populate(model.actionTitle)
        binding.actionButton.doOnClick {
            model.clickAction?.invoke()
        }
    }
}

data class HeaderWithActionItem(
    val title: String,
    val actionTitle: String? = null,
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_header_with_action_item)