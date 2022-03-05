package com.usacheow.coreuiview.uikit.container

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.R as CoreUiViewR

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
) : ViewState(CoreUiViewR.layout.view_view_state_horizontal_list)