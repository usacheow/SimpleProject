package com.usacheow.coreui.uikit.button

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.utils.ext.color
import com.usacheow.coreui.utils.ext.toPx
import kotlinx.android.synthetic.main.view_section_button.view.radioButtonsContainer

private const val ANIMATION_DURATION = 200L
private const val BUTTON_PADDING_DP = 4
private const val BORDER_DP = 1

class SectionButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : MaterialCardView(context, attrs), View.OnClickListener {

    private val borderPx = BORDER_DP.toPx

    private var isClickLocked = false
    var onTabSelectHandler: ((Int) -> Unit)? = null

    override fun onClick(v: View) {
        if (isClickLocked) return

        isClickLocked = true
        val clickPosition = radioButtonsContainer.children.toList().indexOfFirst { v.id == it.id }

        setSelected(clickPosition)
        onTabSelectHandler?.invoke(clickPosition)
        radioButtonsContainer.children.toList()[clickPosition]
            .postDelayed({ isClickLocked = false }, ANIMATION_DURATION)
    }

    fun setSelected(position: Int) {
        radioButtonsContainer.children.toList()
            .filterIsInstance<TextView>()
            .forEachIndexed { index, button ->
                when {
                    position != index -> button.setUnselectedState()
                    else -> button.setSelectedState()
                }
            }
    }

    fun populate(list: List<String>, selectedPosition: Int = 0) {
        radioButtonsContainer.removeAllViews()
        list.forEachIndexed { index, text -> addButton(text, index == list.size + 1) }
        setSelected(selectedPosition)
    }

    private fun addButton(value: String, isLast: Boolean) {
        val padding = BUTTON_PADDING_DP.toPx
        val button = TextView(ContextThemeWrapper(context, R.style.BaseTextButton)).apply {
            updatePadding(padding, padding, padding, padding)
            setOnClickListener(this@SectionButton)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            text = value
            setLines(1)
        }
        radioButtonsContainer.addView(button)
        button.updateLayoutParams<LinearLayout.LayoutParams> {
            height = ViewGroup.LayoutParams.MATCH_PARENT
            weight = 1f
            width = 0
            if (!isLast) updateMargins(right = borderPx)
        }
    }

    private fun TextView.setSelectedState() {
        setBackgroundResource(R.drawable.bg_section_button_selected)
        setTextColor(color(R.color.white))
    }

    private fun TextView.setUnselectedState() {
        with(TypedValue()) {
            context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
            setBackgroundResource(resourceId)
        }
        setTextColor(color(R.color.colorTextSecondary))
    }
}