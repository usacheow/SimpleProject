package com.usacheow.coreui.uikit.listitem

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
import com.usacheow.coreui.uikit.utils.EmptyState
import com.usacheow.coreui.uikit.utils.ImageInfo
import com.usacheow.coreui.uikit.utils.populate
import com.usacheow.coreui.utils.ext.color
import com.usacheow.coreui.utils.ext.doOnClick
import com.usacheow.coreui.utils.ext.makeGone
import com.usacheow.coreui.utils.ext.makeVisible
import com.usacheow.coreui.utils.ext.populate
import com.usacheow.coreui.utils.ext.resize
import com.usacheow.coreui.utils.ext.toPx
import kotlinx.android.synthetic.main.action_item_view.view.actionCheckBox
import kotlinx.android.synthetic.main.action_item_view.view.actionDragFlagView
import kotlinx.android.synthetic.main.action_item_view.view.actionIconView
import kotlinx.android.synthetic.main.action_item_view.view.actionSubtitleView
import kotlinx.android.synthetic.main.action_item_view.view.actionSwitch
import kotlinx.android.synthetic.main.action_item_view.view.actionTitleLayout
import kotlinx.android.synthetic.main.action_item_view.view.actionTitleView

private const val TITLE_SHIMMER_WIDTH_DP = 180

class ActionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ActionItem> {

    private var visibleControlButton: CompoundButton? = null
    private var model: ActionItem? = null

    override fun populate(model: ActionItem) {
        if (model.isShimmer) {
            actionIconView.makeGone()
            actionTitleView.setBackgroundResource(R.drawable.bg_shimmer_line)
            actionTitleLayout.resize(widthPx = TITLE_SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            actionSubtitleView.makeGone()

            showShimmer(true)
        } else {
            actionIconView.makeVisible()
            actionTitleView.background = null
            actionTitleLayout.resize(widthPx = ViewGroup.LayoutParams.MATCH_PARENT, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            actionSubtitleView.makeVisible()

            hideShimmer()
            showData(model)
        }
    }

    private fun showData(model: ActionItem) {
        this.model = model

        actionTitleView.text = model.title
        model.titleColorResId?.let { actionTitleView.setTextColor(color(it)) }
        actionSubtitleView.populate(model.subtitle)
        model.subtitleColorResId?.let { actionSubtitleView.setTextColor(color(it)) }
        actionIconView.populate(model.imageInfo)

        actionDragFlagView.isVisible = model.isDraggable
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
        actionSwitch.makeGone()
        actionCheckBox.makeGone()

        return when (selectionType) {
            ActionSelectionType.CHECK_BOX -> actionCheckBox
            ActionSelectionType.SWITCH -> actionSwitch
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
) : ViewType(R.layout.action_item_view) {

    companion object {
        fun shimmer() = ActionItem(title = "").apply { isShimmer = true }
    }
}

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
    NONE
}