package com.usacheow.coreuiview.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.google.android.material.card.MaterialCardView
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.TagViewState
import com.usacheow.coreuiview.databinding.ViewTagTileBinding
import com.usacheow.coreuiview.tools.resource.ThemeColorsAttrs
import com.usacheow.coreuiview.tools.resource.colorByAttr
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.R as CoreUiViewR

class TagTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialCardView(context, attrs), Populatable<TagTileItem> {

    private val binding by lazy { ViewTagTileBinding.bind(this) }

    override fun populate(model: TagTileItem) {
        binding.nameView.populate(model.name)
        binding.nameView.setTextColor(
            colorByAttr(
                when (model.isSelected) {
                    true -> model.selectedColor.text
                    false -> model.unselectedColor.text
                }
            )
        )
        setCardBackgroundColor(
            colorByAttr(
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
    val unselectedColor: Color = Color(ThemeColorsAttrs.onSecondaryContainer, ThemeColorsAttrs.secondaryContainer),
    val selectedColor: Color = Color(ThemeColorsAttrs.onSecondary, ThemeColorsAttrs.secondary),
    val selectListener: () -> Unit,
) : TagViewState(CoreUiViewR.layout.view_tag_tile) {

    data class Color(
        @AttrRes val text: Int,
        @AttrRes val background: Int,
    )
}