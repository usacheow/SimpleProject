package com.kapmayn.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.utils.populate
import kotlinx.android.synthetic.main.view_divider.view.dividerTitle

class DividerView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_divider, this)
        attrs?.let { applyAttrs(it) }
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DividerView)
        try {
            dividerTitle.populate(typedArray.getText(R.styleable.DividerView_title).toString())
        } catch (e: NullPointerException) {
        } finally {
            typedArray.recycle()
        }
    }
}