package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.TagViewState
import com.usacheow.coreui.databinding.ViewTagTileBinding
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.doOnClick

class TagTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<TagTileItem> {

    private val binding by lazy { ViewTagTileBinding.bind(this) }

    override fun populate(model: TagTileItem) {
        binding.nameView.populate(model.name)
        binding.nameView.setTextColor(
            color(
                when (model.isSelected) {
                    true -> model.selectedColor.text
                    false -> model.unselectedColor.text
                }
            )
        )
        setCardBackgroundColor(
            color(
                when (model.isSelected) {
                    true -> model.selectedColor.background
                    false -> model.unselectedColor.background
                }
            )
        )

        doOnClick {
            if (!model.isSelected) {
                model.selectListener()
            }
            model.clickListener()
        }
    }
}

data class TagTileItem(
    val name: TextSource,
    val unselectedColor: TagColor = TagColor(R.color.text, R.color.surfaceSecondary),
    val selectedColor: TagColor = TagColor(R.color.textInverse, R.color.surfaceSecondaryVariant),
    val selectListener: () -> Unit,
) : TagViewState(R.layout.view_tag_tile)

data class TagColor(
    val text: Int,
    val background: Int,
)