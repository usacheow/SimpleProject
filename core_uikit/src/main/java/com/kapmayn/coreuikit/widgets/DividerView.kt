package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import com.kapmayn.coreuikit.utils.populate
import kotlinx.android.synthetic.main.view_divider.view.dividerTitle

class DividerView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr), Populatable<DividerItem> {

    override fun populate(model: DividerItem) {
        dividerTitle.populate(model.message)
    }
}

data class DividerItem(
    val message: String
) : ViewType(R.layout.view_divider)