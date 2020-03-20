package com.usacheow.coreuikit.widgets.lists

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypesAdapter
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_type_list_view.view.listView

@Deprecated("")
class ViewTypeListView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Populatable<ViewTypeListViewObject> {

    override fun populate(model: ViewTypeListViewObject) {
        with(listView) {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = ViewTypesAdapter(model.items)
        }
    }
}

data class ViewTypeListViewObject(
    val items: List<ViewType>
) : ViewType(R.layout.view_type_list_view)