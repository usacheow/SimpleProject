package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.RadioViewType
import com.usacheow.coreui.databinding.ViewTagItemBinding
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.populate

class TagItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<TagItem> {

    private val binding by lazy { ViewTagItemBinding.bind(this) }

    override fun populate(model: TagItem) {
        binding.tagItemNameView.populate(model.name)
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