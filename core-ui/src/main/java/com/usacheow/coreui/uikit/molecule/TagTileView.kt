package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.core.TextSource
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.TagViewState
import com.usacheow.coreui.databinding.ViewTagTileBinding
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate

class TagTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialCardView(context, attrs), Populatable<TagTileItem> {

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
    val unselectedColor: TagColor = TagColor(CoreUiR.color.onSecondaryContainer, CoreUiR.color.secondaryContainer),
    val selectedColor: TagColor = TagColor(CoreUiR.color.onSecondary, CoreUiR.color.secondary),
    val selectListener: () -> Unit,
) : TagViewState(CoreUiR.layout.view_tag_tile)

data class TagColor(
    val text: Int,
    val background: Int,
)