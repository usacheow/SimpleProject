package com.usacheow.coreui.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.uikit.helper.toPx

class SpaceTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : View(context, attributeSet), Populatable<SpaceTileItem> {

    override fun populate(model: SpaceTileItem) {
        layoutParams = layoutParams.apply { height = model.heightDp.toPx }
    }
}

data class SpaceTileItem(
    val heightDp: Int,
) : ViewState(CoreUiR.layout.view_space_tile)