package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewActionTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val ICON_PADDING_DP = 4
private const val DEFAULT_PADDING_DP = 0

class ActionTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<ActionTileItem> {

    private val binding by lazy { ViewActionTileBinding.bind(this) }

    override fun populate(model: ActionTileItem) {
        binding.imageView.apply {
            apply(model.image)
            updatePadding(model.image)
        }
        binding.titleView.apply(model.title)
        binding.subtitleView.apply(model.subtitle)
        populateClickListener(model)
    }

    private fun populateClickListener(model: ActionTileItem) {
        binding.switchView.makeGone()
        binding.checkBox.makeGone()

        if (model.isShimmer) {
            setListenerIfNeed(null)
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

            setListenerIfNeed { visibleControlButton.performClick() }
        }
    }

    private  fun ImageView.updatePadding(imageInfo: ImageInfo) {
        val padding = when (imageInfo) {
            is IconInfo -> ICON_PADDING_DP
            else -> DEFAULT_PADDING_DP
        }.toPx
        updatePadding(padding, padding, padding, padding)
    }
}

data class ActionTileItem(
    val image: ImageInfo = EmptyInfo(),
    val title: TextInfo,
    val subtitle: TextInfo? = null,
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