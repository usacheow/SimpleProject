package com.usacheow.apptest.details

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.apptest.R
import com.usacheow.apptest.databinding.ViewWarningShimmerTileBinding
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.utils.view.*

class WarningShimmerTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<WarningShimmerTileItem> {

    private val binding by lazy { ViewWarningShimmerTileBinding.bind(this) }

    override fun populate(model: WarningShimmerTileItem) {
        binding.shimmer.setShimmer(true)
    }
}

object WarningShimmerTileItem : ViewType(R.layout.view_warning_shimmer_tile)