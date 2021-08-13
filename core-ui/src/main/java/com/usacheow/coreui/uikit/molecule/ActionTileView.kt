package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.databinding.ViewActionTileBinding
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible

class ActionTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr), Populatable<ActionTileItem> {

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
) : ViewType(R.layout.view_action_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, rightIcon = false)
    }
}

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
}