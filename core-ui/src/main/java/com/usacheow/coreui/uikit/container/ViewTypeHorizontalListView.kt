package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewViewStateHorizontalListBinding

class ViewTypeHorizontalListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs), Populatable<ViewTypeHorizontalListItem> {

    private val binding by lazy { ViewViewStateHorizontalListBinding.bind(this) }

    override fun populate(model: ViewTypeHorizontalListItem) {
        with(binding.listView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = ViewStateAdapter(model.items)
        }
    }
}

data class ViewTypeHorizontalListItem(
    val items: List<ViewState>
) : ViewState(CoreUiR.layout.view_view_state_horizontal_list)