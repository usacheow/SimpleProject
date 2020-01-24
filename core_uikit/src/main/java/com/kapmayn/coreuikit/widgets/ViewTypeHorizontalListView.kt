package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.adapters.ViewTypesAdapter
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_type_horizontal_list_view.view.listView

class ViewTypeHorizontalListView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Populatable<ViewTypeHorizontalListItem> {

    override fun populate(model: ViewTypeHorizontalListItem) {
        with(listView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = ViewTypesAdapter(model.items)
        }
    }
}

data class ViewTypeHorizontalListItem(
    val items: List<ViewType>
) : ViewType(R.layout.view_type_horizontal_list_view)