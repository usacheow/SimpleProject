package com.usacheow.coreui.uikit.decoration

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.view.toPx

class SpaceView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributeSet, defStyleAttr), Populatable<SpaceItem> {

    override fun populate(model: SpaceItem) {
        layoutParams = layoutParams.apply { height = model.heightDp.toPx }
    }
}

data class SpaceItem(
    val heightDp: Int
) : ViewType(R.layout.view_space)