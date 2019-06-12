package com.kapmayn.coreuikit.views

import android.content.Context
import android.text.SpannedString
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.IPopulatable
import com.kapmayn.coreuikit.utils.populate
import kotlinx.android.synthetic.main.view_two_line_info.view.twoLineInfoDescription
import kotlinx.android.synthetic.main.view_two_line_info.view.twoLineInfoTitle

private const val TITLE_DEFAULT_VALUE = ""
private const val DESCRIPTION_DEFAULT_VALUE = ""
private const val IS_TEXT_IN_CENTER_DEFAULT_VALUE = true

class TwoLineInfoView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), IPopulatable<TwoLineInfoViewObject> {

    init {
        View.inflate(context, R.layout.view_two_line_info, this)
        attrs?.let { applyAttrs(it) }
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TwoLineInfoView)
        try {
            val title = typedArray.getText(R.styleable.TwoLineInfoView_tliv_title)?.toString() ?: TITLE_DEFAULT_VALUE
            val description = typedArray.getText(R.styleable.TwoLineInfoView_tliv_description)?.toString()
                ?: DESCRIPTION_DEFAULT_VALUE
            val isTextInCenter = typedArray.getBoolean(R.styleable.TwoLineInfoView_tliv_text_in_center, IS_TEXT_IN_CENTER_DEFAULT_VALUE)

            val model = TwoLineInfoViewObject(title, description, isTextInCenter)
            populate(model)
        } catch (e: NullPointerException) {
        } finally {
            typedArray.recycle()
        }
    }

    override fun populate(model: TwoLineInfoViewObject) {
        val textGravity = when (model.isTextInCenter) {
            true -> Gravity.CENTER_HORIZONTAL
            false -> Gravity.START
        }

        twoLineInfoTitle.populate(model.title)
        twoLineInfoTitle.gravity = textGravity

        twoLineInfoDescription.populate(model.description)
        twoLineInfoDescription.gravity = textGravity
    }

    fun populateTitle(title: String) {
        twoLineInfoTitle.populate(title)
    }

    fun populateTitle(title: SpannedString) {
        twoLineInfoTitle.populate(title)
    }
}

data class TwoLineInfoViewObject(
    val title: String,
    val description: String,
    val isTextInCenter: Boolean = IS_TEXT_IN_CENTER_DEFAULT_VALUE
)