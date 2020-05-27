package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.RadioViewType
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.populate
import kotlinx.android.synthetic.main.view_tag_item.view.tagItemNameView

class TagItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<TagItem> {

    override fun populate(model: TagItem) {
        tagItemNameView.populate(model.name)
        setCardBackgroundColor(color(when (model.isSelected) {
            true -> R.color.disabled
            false -> R.color.colorDivider
        }))

        if (model.isSelected) {
            setOnClickListener(null)
        } else {
            setOnClickListener {
                model.onSelectAction()
                model.onClickAction()
            }
        }
    }
}

data class TagItem(
    val name: String,
    val onClickAction: () -> Unit
) : RadioViewType(R.layout.view_tag_item)