package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.toPx

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