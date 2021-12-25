package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.R as CoreUiR

class ViewStateHorizontalListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : RecyclerView(context, attrs), Populatable<ViewStateHorizontalListItem> {

    override fun populate(model: ViewStateHorizontalListItem) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        isNestedScrollingEnabled = false
        adapter = ViewStateAdapter(model.items)
    }
}

data class ViewStateHorizontalListItem(
    val items: List<ViewState>
) : ViewState(CoreUiR.layout.view_view_state_horizontal_list)