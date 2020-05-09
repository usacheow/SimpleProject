package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.view.isVisible
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.makeGone
import com.usacheow.coreuikit.utils.ext.makeVisible
import com.usacheow.coreuikit.utils.ext.populate
import kotlinx.android.synthetic.main.action_item_view.view.actionCheckBox
import kotlinx.android.synthetic.main.action_item_view.view.actionDragFlagView
import kotlinx.android.synthetic.main.action_item_view.view.actionIconView
import kotlinx.android.synthetic.main.action_item_view.view.actionSubtitleView
import kotlinx.android.synthetic.main.action_item_view.view.actionSwitch
import kotlinx.android.synthetic.main.action_item_view.view.actionTitleView

class ActionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<ActionItem> {

    private var visibleControlButton: CompoundButton? = null
    private var model: ActionItem? = null

    override fun populate(model: ActionItem) {
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
) : ViewType(R.layout.action_item_view)

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
    NONE
}