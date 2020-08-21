package com.usacheow.coreui.uikit.header

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.populate
import kotlinx.android.synthetic.main.view_header_with_action_item.view.actionButton
import kotlinx.android.synthetic.main.view_header_with_action_item.view.headerView

class HeaderWithActionView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr), Populatable<HeaderWithActionItem> {

    override fun populate(model: HeaderWithActionItem) {
        headerView.text = model.title
        actionButton.populate(model.actionTitle)
        actionButton.doOnClick {
            model.clickAction?.invoke()
        }
    }
}

data class HeaderWithActionItem(
    val title: String,
    val actionTitle: String? = null,
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_header_with_action_item)