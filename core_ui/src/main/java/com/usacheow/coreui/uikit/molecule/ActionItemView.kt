package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.annotation.ColorRes
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewActionItemBinding
import com.usacheow.coreui.utils.EmptyState
import com.usacheow.coreui.utils.ImageInfo
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.*

private const val TITLE_SHIMMER_WIDTH_DP = 180

class ActionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ActionItem> {

    private var visibleControlButton: CompoundButton? = null
    private var model: ActionItem? = null

    private val binding by lazy { ViewActionItemBinding.bind(this) }

    override fun populate(model: ActionItem) {
        if (model.isShimmer) {
            binding.actionIconView.makeGone()
            binding.actionTitleView.setBackgroundResource(R.drawable.bg_shimmer_line)
            binding.actionTitleLayout.resize(widthPx = TITLE_SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionSubtitleView.makeGone()

            showShimmer(true)
        } else {
            binding.actionIconView.makeVisible()
            binding.actionTitleView.background = null
            binding.actionTitleLayout.resize(widthPx = ViewGroup.LayoutParams.MATCH_PARENT, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionSubtitleView.makeVisible()

            hideShimmer()
            showData(model)
        }
    }

    private fun showData(model: ActionItem) {
        this.model = model

        binding.actionTitleView.text = model.title
        model.titleColorResId?.let { binding.actionTitleView.setTextColor(color(it)) }
        binding.actionSubtitleView.populate(model.subtitle)
        model.subtitleColorResId?.let { binding.actionSubtitleView.setTextColor(color(it)) }
        binding.actionIconView.populate(model.imageInfo)

        binding.actionDragFlagView.isVisible = model.isDraggable
        visibleControlButton = setupControl(model.isChecked, model.selectionType, model.onControlClicked)

        if (model.onItemClicked == null && model.onControlClicked == null) {
            setOnClickListener(null)
            isEnabled = false
            return
        }

        isEnabled = true
        doOnClick {
            model.onItemClicked?.invoke() ?: visibleControlButton?.performClick()
        }
    }

    private fun setupControl(
        isSelected: Boolean,
        selectionType: ActionSelectionType,
        onClicked: ((Boolean) -> Unit)?
    ): CompoundButton? {
        binding.actionSwitch.makeGone()
        binding.actionCheckBox.makeGone()

        return when (selectionType) {
            ActionSelectionType.CHECK_BOX -> binding.actionCheckBox
            ActionSelectionType.SWITCH -> binding.actionSwitch
            ActionSelectionType.NONE -> null
        }?.apply {
            makeVisible()
            isChecked = isSelected
            setOnCheckedChangeListener { _, isChecked ->
                model?.isChecked = isChecked
                onClicked?.invoke(isChecked)
            }
        }
    }
}

data class ActionItem(
    val imageInfo: ImageInfo = EmptyState(),
    val title: String,
    val subtitle: String? = null,
    @ColorRes val titleColorResId: Int? = null,
    @ColorRes val subtitleColorResId: Int? = null,
    val isDraggable: Boolean = false,
    var isChecked: Boolean = false,
    val selectionType: ActionSelectionType = ActionSelectionType.NONE,
    val onItemClicked: (() -> Unit)? = null,
    val onControlClicked: ((Boolean) -> Unit)? = null
) : ViewType(R.layout.view_action_item) {

    companion object {
        fun shimmer() = ActionItem(title = "").apply { isShimmer = true }
    }
}

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
    NONE
}