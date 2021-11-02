package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewActionTileBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.makeGone
import com.usacheow.coreui.uikit.helper.makeVisible
import com.usacheow.coreui.uikit.helper.populate

class ActionTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs), Populatable<ActionTileItem> {

    private val binding by lazy { ViewActionTileBinding.bind(this) }

    override fun populate(model: ActionTileItem) {
        binding.imageView.populate(model.image)
        binding.titleView.populate(model.title)
        binding.subtitleView.populate(model.subtitle)
        populateClickListener(model)
    }

    private fun populateClickListener(model: ActionTileItem) {
        binding.switchView.makeGone()
        binding.checkBox.makeGone()

        if (model.isShimmer) {
            doOnClick(null)
        } else {
            val visibleControlButton = when (model.selectionType) {
                ActionSelectionType.CHECK_BOX -> binding.checkBox
                ActionSelectionType.SWITCH -> binding.switchView
            }.apply {
                makeVisible()
                isChecked = isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    model.isChecked = isChecked
                    model.clickListener.invoke(isChecked)
                }
            }

            doOnClick { visibleControlButton.performClick() }
        }
    }
}

data class ActionTileItem(
    val image: ImageSource = ImageSource.Empty,
    val title: TextSource,
    val subtitle: TextSource? = null,
    var isChecked: Boolean = false,
    val selectionType: ActionSelectionType = ActionSelectionType.CHECK_BOX,
    val clickListener: (Boolean) -> Unit = {},
) : ViewState(CoreUiR.layout.view_action_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, rightIcon = false)
    }
}

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
}