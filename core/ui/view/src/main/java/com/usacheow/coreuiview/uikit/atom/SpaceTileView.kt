package com.usacheow.coreuiview.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.tools.resource.toPx
import com.usacheow.coreuiview.R as CoreUiViewR

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
) : ViewState(CoreUiViewR.layout.view_space_tile)