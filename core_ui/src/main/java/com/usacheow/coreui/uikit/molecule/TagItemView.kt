package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.TagViewType
import com.usacheow.coreui.databinding.ViewTagItemBinding
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.setListenerIfNeed

class TagItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<TagItem> {

    private val binding by lazy { ViewTagItemBinding.bind(this) }

    override fun populate(model: TagItem) {
        binding.nameView.populate(model.name)
        binding.nameView.setTextColor(color(when (model.isSelected) {
            true -> model.selectedColor.text
            false -> model.unselectedColor.text
        }))
        setCardBackgroundColor(color(when (model.isSelected) {
            true -> model.selectedColor.background
            false -> model.unselectedColor.background
        }))

        if (model.isSelected) {
            setListenerIfNeed {
                model.onSelectAction()
            }
        } else {
            setListenerIfNeed {
                model.onSelectAction()
                model.onClickAction()
            }
        }
    }
}

data class TagItem(
    val name: TextSource,
    val unselectedColor: TagColor = TagColor(R.color.colorText, R.color.colorDivider),
    val selectedColor: TagColor = TagColor(R.color.colorTextInverse, R.color.disabled),
    val onClickAction: () -> Unit,
) : TagViewType(R.layout.view_tag_item)

data class TagColor(
    val text: Int,
    val background: Int,
)