package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewTypeHorizontalListViewBinding

class ViewTypeHorizontalListView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), Populatable<ViewTypeHorizontalListItem> {

    private val binding by lazy { ViewTypeHorizontalListViewBinding.bind(this) }

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
) : ViewState(CoreUiR.layout.view_type_horizontal_list_view)