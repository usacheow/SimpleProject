package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.view.updatePadding
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewActionItemBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*
import java.lang.ref.WeakReference

private const val TEXT_SHIMMER_WIDTH_DP = 180
private const val ICON_PADDING_DP = 4
private const val DEFAULT_PADDING_DP = 0

class ActionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ActionItem> {

    private val binding by lazy { ViewActionItemBinding.bind(this) }

    private val textShimmerWidthPx by lazy { TEXT_SHIMMER_WIDTH_DP.toPx }

    override fun populate(model: ActionItem) {
        populateImage(model)
        populateTitle(model)
        populateSubtitle(model)
        populateClickListener(model)
        setShimmer(model.isShimmer)
    }

    private fun populateClickListener(model: ActionItem) {
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
                    model.onControlClicked.invoke(isChecked)
                }
            }

//            setListenerIfNeed { visibleControlButton.performClick() }
        }
    }

    private fun populateImage(model: ActionItem) = with (binding.imageView) {
        fun ImageView.updatePadding(imageInfo: ImageInfo) {
            val padding = when (imageInfo) {
                is IconInfo -> ICON_PADDING_DP
                else -> DEFAULT_PADDING_DP
            }.toPx
            updatePadding(padding, padding, padding, padding)
        }

        if (model.isShimmer) {
            showCircleShimmer()
            updatePadding(EmptyInfo())
        } else {
            hideShimmer(model.image)
            updatePadding(model.image)
        }
    }

    private fun populateTitle(model: ActionItem) = with (binding.titleView) {
        if (model.isShimmer) {
            showShimmer(widthPx = textShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.MATCH_PARENT)
            apply(model.title)
        }
    }

    private fun populateSubtitle(model: ActionItem) = with (binding.subtitleView) {
        if (model.isShimmer) {
            showShimmer(widthPx = textShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.MATCH_PARENT)
            apply(model.subtitle)
        }
    }
}

data class ActionItem(
    val image: ImageInfo = EmptyInfo(),
    val title: TextInfo,
    val subtitle: TextInfo? = null,
    var isChecked: Boolean = false,
    val selectionType: ActionSelectionType = ActionSelectionType.CHECK_BOX,
    val onControlClicked: (Boolean) -> Unit = {}
) : ViewType(R.layout.view_action_item) {

    companion object {
        fun shimmer() = ActionItem(title = TextInfo(TextString(""))).apply { isShimmer = true }
    }
}

enum class ActionSelectionType {
    CHECK_BOX,
    SWITCH,
}