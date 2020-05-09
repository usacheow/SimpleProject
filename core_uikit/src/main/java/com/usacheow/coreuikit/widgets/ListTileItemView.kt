package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.populate
import kotlinx.android.synthetic.main.list_tile_item_view.view.actionDescriptionView
import kotlinx.android.synthetic.main.list_tile_item_view.view.actionIconView
import kotlinx.android.synthetic.main.list_tile_item_view.view.actionSubtitleView
import kotlinx.android.synthetic.main.list_tile_item_view.view.actionTitleView

class ListTileItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<ListTileItem> {

    override fun populate(model: ListTileItem) {
        actionTitleView.text = model.title
        actionSubtitleView.populate(model.subtitle)
        actionDescriptionView.populate(model.description)
        actionIconView.populate(model.imageInfo)

        isEnabled = if (model.onItemClicked == null) {
            setOnClickListener(null)
            false
        } else {
            doOnClick { model.onItemClicked.invoke() }
            true
        }
    }
}

data class ListTileItem(
    val imageInfo: ImageInfo = EmptyState(),
    val title: String? = null,
    val subtitle: String,
    val description: String? = null,
    val onItemClicked: (() -> Unit)? = null
) : ViewType(R.layout.list_tile_item_view)