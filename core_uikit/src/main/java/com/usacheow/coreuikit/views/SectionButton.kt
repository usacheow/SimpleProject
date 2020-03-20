package com.usacheow.coreuikit.views

import android.content.Context
import android.util.AttributeSet
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
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.toPx
import kotlinx.android.synthetic.main.view_section_button.view.radioButtonsContainer

private const val ANIMATION_DURATION = 200L
private const val BUTTON_PADDING_DP = 4
private const val BORDER_DP = 1

class SectionButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : MaterialCardView(context, attrs), View.OnClickListener {

    private val borderPx = BORDER_DP.toPx

    private var isClickLocked = false
    private var onTabSelectHandler: ((Int) -> Unit)? = null

    override fun onClick(v: View) {
        if (isClickLocked) return

        isClickLocked = true
        val clickPosition = radioButtonsContainer.children.toList().indexOfFirst { v.id == it.id }

        setSelected(clickPosition)
        onTabSelectHandler?.invoke(clickPosition)
        radioButtonsContainer.children.toList()[clickPosition]
            .postDelayed({ isClickLocked = false }, ANIMATION_DURATION)
    }

    fun setSelected(position: Int, withoutAnimation: Boolean = false) {
        radioButtonsContainer.children.toList()
            .filterIsInstance<TextView>()
            .forEachIndexed { index, button ->
                when {
                    position != index -> button.changeState(State.UNSELECTED)
                    withoutAnimation -> button.changeState(State.SELECTED)
                    else -> button.selectWithAnimation()
                }
            }
    }

    fun populate(list: List<String>) {
        radioButtonsContainer.removeAllViews()
        list.forEachIndexed { index, text -> addButton(text, index == list.size + 1) }
        setSelected(0)
    }

    private fun addButton(value: String, isLast: Boolean) {
        val padding = BUTTON_PADDING_DP.toPx
        val button = TextView(ContextThemeWrapper(context, R.style.TextButton)).apply {
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

    private fun TextView.selectWithAnimation() {
        animate().alpha(0.5f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction {
                changeState(State.SELECTED)
                animate().alpha(1.0f)
                    .setDuration((ANIMATION_DURATION / 2))
                    .setListener(null)
            }
    }

    private fun TextView.changeState(state: State) {
        setBackgroundColor(color(state.colorBackground))
        setTextColor(color(state.colorText))
    }

    enum class State(val colorBackground: Int, val colorText: Int) {
        SELECTED(R.color.colorAccent, R.color.white),
        UNSELECTED(R.color.white, R.color.colorAccent)
    }
}