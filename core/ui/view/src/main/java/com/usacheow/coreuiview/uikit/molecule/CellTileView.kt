package com.usacheow.coreuiview.uikit.molecule

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.corecommon.container.CombineIcon
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.databinding.ViewCellItemBinding
import com.usacheow.coreuiview.tools.MarginValues
import com.usacheow.coreuiview.tools.resource.ThemeColorsAttrs
import com.usacheow.coreuiview.tools.applyPadding
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.makeGone
import com.usacheow.coreuiview.tools.makeVisible
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.tools.resource.get
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@SuppressLint("UnsafeOptInUsageError")
class CellTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayoutCompat(context, attrs), Populatable<CellTileItem> {

    private val binding by lazy { ViewCellItemBinding.bind(this) }

    override fun populate(model: CellTileItem) {
        fillLeftPart(model.leftPart)
        fillMiddlePart(model)
        fillRightPart(model.rightPart)
        fillClickListeners(
            mainClickListener = model.mainClickListener,
            rightClickListener = model.rightClickListener,
        )
    }

    private fun fillLeftPart(leftPart: CellLeftPart?) {
        binding.leftView.makeVisible()
        binding.leftIconView.makeGone()
        binding.leftLogoView.makeGone()
        when (leftPart) {
            null -> {
                binding.leftView.makeGone()
            }

            is CellLeftPart.Icon -> {
                binding.leftIconView.makeVisible()
                binding.leftIconView.populate(leftPart.toCombineIcon())
            }

            is CellLeftPart.Logo -> {
                binding.leftIconView.makeVisible()
                binding.leftLogoView.populate(leftPart.source)
            }
        }
    }

    private fun fillMiddlePart(model: CellTileItem) {
        binding.subtitleView.populate(model.subtitle)
        binding.titleView.populate(model.title)
        binding.valueView.populate(model.value)
        binding.additionalView.populate(model.additional)
        binding.linkView.populate(model.link)

        binding.subtitleView.setTextColor(model.contentColors.subtitleColorSource.get(context))
        binding.titleView.setTextColor(model.contentColors.titleColorSource.get(context))
        binding.valueView.setTextColor(model.contentColors.valueColorSource.get(context))
        binding.additionalView.setTextColor(model.contentColors.additionalColorSource.get(context))
    }

    private fun fillRightPart(rightPart: CellRightPart?) {
        binding.rightView.makeVisible()
        binding.rightIconView.makeGone()
        binding.rightLogoView.makeGone()
        binding.switchView.makeGone()
        when (rightPart) {
            null -> {
                binding.rightView.makeGone()
            }

            is CellRightPart.ActionIcon -> {
                binding.rightIconView.makeVisible()
                binding.rightIconView.populate(rightPart.source)
            }

            is CellRightPart.Logo -> {
                binding.rightLogoView.makeVisible()
                binding.rightLogoView.populate(rightPart.source)
            }

            is CellRightPart.Switch -> {
                binding.switchView.makeVisible()
                binding.switchView.isChecked = rightPart.isChecked
            }
        }

        val middleViewRightMargin = when (binding.rightView.isVisible) {
            true -> 0
            false -> resources.getDimensionPixelOffset(CoreUiThemeR.dimen.left_right_margin)
        }
        binding.middleView.applyPadding(MarginValues(end = middleViewRightMargin))
    }

    private fun fillClickListeners(
        mainClickListener: (() -> Unit)?,
        rightClickListener: (() -> Unit)?,
    ) {
        binding.root.isClickable = false
        binding.root.isEnabled = false
        binding.mainClickablePartView.isClickable = false
        binding.mainClickablePartView.isEnabled = false
        binding.rightView.isClickable = false
        binding.rightView.isEnabled = false
        if (mainClickListener != null && rightClickListener == null) {
            binding.root.isClickable = true
            binding.root.isEnabled = true
            binding.root.doOnClick(mainClickListener)
        } else if (mainClickListener == null && rightClickListener != null) {
            binding.root.isClickable = true
            binding.root.isEnabled = true
            binding.root.doOnClick(rightClickListener)
        } else if (mainClickListener != null && rightClickListener != null) {
            binding.mainClickablePartView.isClickable = true
            binding.mainClickablePartView.isEnabled = true
            binding.mainClickablePartView.doOnClick(mainClickListener)
            binding.rightView.isClickable = true
            binding.rightView.isEnabled = true
            binding.rightView.doOnClick(rightClickListener)
        }
    }
}

data class CellTileItem(
    val leftPart: CellLeftPart? = null,
    val subtitle: TextSource? = null,
    val title: TextSource? = null,
    val value: TextSource? = null,
    val additional: TextSource? = null,
    val link: TextSource? = null,
    val rightPart: CellRightPart? = null,
    val contentColors: CellItemContentColors = CellItemContentColors(),
    val mainClickListener: (() -> Unit)? = null,
    val rightClickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_cell_item) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, rightIcon = false)
    }
}

sealed class CellLeftPart {

    data class Icon(
        val icon: ImageSource.Res,
        val background: ImageSource.Res? = null,
    ) : CellLeftPart() {
        fun toCombineIcon() = CombineIcon(icon, background)
    }
    data class Logo(val source: ImageSource) : CellLeftPart()
}

sealed class CellRightPart {

    data class ActionIcon(val source: ImageSource) : CellRightPart()
    data class Logo(val source: ImageSource) : CellRightPart()
    data class Switch(val isChecked: Boolean) : CellRightPart()
}

data class CellItemContentColors(
    val subtitleColorSource: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolSecondary),
    val titleColorSource: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolPrimary),
    val valueColorSource: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolPrimary),
    val additionalColorSource: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolSecondary),
)
