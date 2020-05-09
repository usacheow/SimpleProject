package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.resize
import com.usacheow.coreuikit.utils.ext.toPx
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoHeaderView
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoValueView

class SmallInfoCardItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<SmallInfoCardItem> {

    override fun populate(model: SmallInfoCardItem) {
        smallInfoHeaderView.text = model.header
        smallInfoValueView.text = model.value

        resize(widthPx = when {
            model.widthDp != null -> model.widthDp.toPx
            model.needExpandOnWidth -> ViewGroup.LayoutParams.MATCH_PARENT
            else -> ViewGroup.LayoutParams.WRAP_CONTENT
        }, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

        model.clickAction?.let { action ->
            doOnClick { action() }
        }
    }
}

data class SmallInfoCardItem(
    val header: String,
    val value: String,
    val widthDp: Int? = null,
    val needExpandOnWidth: Boolean = false,
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_small_info_card)