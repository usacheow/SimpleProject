package com.kapmayn.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.utils.makeGone
import com.kapmayn.coreuikit.utils.makeVisible
import com.kapmayn.coreuikit.utils.populate
import kotlinx.android.synthetic.main.view_two_line_button.view.twoLineButtonIcon
import kotlinx.android.synthetic.main.view_two_line_button.view.twoLineButtonSubtitle
import kotlinx.android.synthetic.main.view_two_line_button.view.twoLineButtonTitle

private const val TITLE_DEFAULT_VALUE = ""
private const val SUBTITLE_DEFAULT_VALUE = ""
private const val ICON_RES_ID_DEFAULT_VALUE = -1
private const val IS_CLICKABLE_DEFAULT_VALUE = true

class TwoLineButtonView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_two_line_button, this)
        attrs?.let { applyAttrs(it) }
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TwoLineButtonView)
        try {
            val title = typedArray.getText(R.styleable.TwoLineButtonView_tlbv_title)?.toString() ?: TITLE_DEFAULT_VALUE
            val subtitle = typedArray.getText(R.styleable.TwoLineButtonView_tlbv_subtitle)?.toString()
                ?: SUBTITLE_DEFAULT_VALUE
            val iconResId = typedArray.getResourceId(R.styleable.TwoLineButtonView_tlbv_icon, ICON_RES_ID_DEFAULT_VALUE)
            val isClickable = typedArray.getBoolean(R.styleable.TwoLineButtonView_tlbv_is_clickable, IS_CLICKABLE_DEFAULT_VALUE)

            val model = TwoLineButtonViewObject(title, subtitle, iconResId, isClickable)
            populate(model)
        } catch (e: NullPointerException) {
        } finally {
            typedArray.recycle()
        }
    }

    fun populate(model: TwoLineButtonViewObject, onClickAction: () -> Unit = {}) {
        twoLineButtonTitle.populate(model.title)
        twoLineButtonSubtitle.populate(model.subtitle)

        if (model.iconResId == ICON_RES_ID_DEFAULT_VALUE) {
            twoLineButtonIcon.makeGone()
        } else {
            twoLineButtonIcon.setImageResource(model.iconResId)
            twoLineButtonIcon.makeVisible()
        }

        val background = when (model.isClickable) {
            true -> R.drawable.ripple_rectangle
            false -> 0
        }
        setBackgroundResource(background)

        setOnClickListener { onClickAction() }
    }
}

data class TwoLineButtonViewObject(
    val title: String,
    val subtitle: String,
    @DrawableRes val iconResId: Int = ICON_RES_ID_DEFAULT_VALUE,
    val isClickable: Boolean = IS_CLICKABLE_DEFAULT_VALUE
)