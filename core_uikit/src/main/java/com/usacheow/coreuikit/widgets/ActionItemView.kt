package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.annotation.DimenRes
import androidx.annotation.StyleRes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.load
import com.usacheow.coreuikit.utils.ext.makeGone
import com.usacheow.coreuikit.utils.ext.makeVisible
import com.usacheow.coreuikit.utils.ext.populate
import com.usacheow.coreuikit.utils.ext.resize
import com.usacheow.coreuikit.utils.ext.toPx
import kotlinx.android.synthetic.main.action_item_view.view.actionCheckBox
import kotlinx.android.synthetic.main.action_item_view.view.actionDragFlagView
import kotlinx.android.synthetic.main.action_item_view.view.actionIconShapeView
import kotlinx.android.synthetic.main.action_item_view.view.actionIconView
import kotlinx.android.synthetic.main.action_item_view.view.actionRootView
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

        showImage(model)
        actionTitleView.text = model.title
        TextViewCompat.setTextAppearance(actionTitleView, model.titleStyleResId)
        actionSubtitleView.populate(model.subtitle)
        TextViewCompat.setTextAppearance(actionSubtitleView, model.subtitleStyleResId)

        actionRootView.gravity = when (model.subtitle.isNullOrEmpty()) {
            true -> Gravity.CENTER_VERTICAL
            false -> Gravity.TOP
        }

        actionDragFlagView.isVisible = model.isDraggable
        visibleControlButton = setupControl(model.isChecked, model.selectionType, model.onControlClicked)

        if (model.onItemClicked == null && model.onControlClicked == null) {
            setOnClickListener(null)
            isEnabled = false
            return
        }
        isEnabled = true
        setOnClickListener {
            model.onItemClicked?.invoke() ?: visibleControlButton?.performClick()
        }
    }

    private fun showImage(model: ActionItem) {
        actionIconShapeView.resize(model.imageSize.sizeDp.toPx, model.imageSize.sizeDp.toPx)
        actionIconShapeView.makeVisible()

        actionIconShapeView.radius = resources.getDimension(model.iconRadius)
        if (!model.imageUrl.isNullOrBlank()) actionIconView.load(model.imageUrl)
        else if (model.imageResId != null) actionIconView.setImageResource(model.imageResId)
        else actionIconShapeView.makeGone()
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
    val imageUrl: String? = null,
    val imageResId: Int? = null,
    val imageSize: ActionIconSize = ActionIconSize.BIG,
    val title: String,
    val subtitle: String? = null,
    @DimenRes val iconRadius: Int = R.dimen.radius_8,
    @StyleRes val titleStyleResId: Int = R.style.TextBody1,
    @StyleRes val subtitleStyleResId: Int = R.style.TextBody2_60,
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

enum class ActionIconSize(val sizeDp: Int) {
    SMALL(24),
    BIG(48)
}