package com.usacheow.coreui.uikit.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewTypeHorizontalListViewBinding

class ViewTypeHorizontalListView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Populatable<ViewTypeHorizontalListItem> {

    private val binding by lazy { ViewTypeHorizontalListViewBinding.bind(this) }

    override fun populate(model: ViewTypeHorizontalListItem) {
        with(binding.listView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = ViewTypesAdapter(model.items)
        }
    }
}

data class ViewTypeHorizontalListItem(
    val items: List<ViewType>
) : ViewType(R.layout.view_type_horizontal_list_view)