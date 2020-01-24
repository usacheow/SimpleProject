package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoHeaderView
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoValueView

class SmallInfoCardItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<SmallInfoCardItem> {

    override fun populate(model: SmallInfoCardItem) {
        smallInfoHeaderView.text = model.header
        smallInfoValueView.text = model.value

        model.clickAction?.let { action ->
            setOnClickListener { action() }
        }
    }
}

data class SmallInfoCardItem(
    val header: String,
    val value: String,
    val needExpandOnWidth: Boolean = false,
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_small_info_card)