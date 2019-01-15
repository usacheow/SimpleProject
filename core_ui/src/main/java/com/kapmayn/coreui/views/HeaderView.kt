package com.kapmayn.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.kapmayn.coreui.R
import com.kapmayn.coreui.utils.getStatusBarHeight
import com.kapmayn.coreui.utils.makeVisible
import com.kapmayn.coreui.utils.populate
import com.kapmayn.coreui.utils.setTopPadding
import kotlinx.android.synthetic.main.view_header.view.headerActionButton
import kotlinx.android.synthetic.main.view_header.view.headerMenuFirstButton
import kotlinx.android.synthetic.main.view_header.view.headerMenuSecondButton
import kotlinx.android.synthetic.main.view_header.view.headerText

class HeaderView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_header, this)
        attrs?.let { applyAttrs(it) }
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView)
        try {
            headerText.populate(typedArray.getText(R.styleable.HeaderView_header).toString())
            setTopPadding(getStatusBarHeight())
        } catch (e: NullPointerException) {
        } finally {
            typedArray.recycle()
        }
    }

    fun populate(model: String) {
        headerText.text = model
    }

    fun setActionButton(@DrawableRes actionIcon: Int, action: () -> Unit) {
        headerActionButton.makeVisible()
        headerActionButton.setImageResource(actionIcon)
        headerActionButton.setOnClickListener { action() }
    }

    fun setMenuFirstButton(@DrawableRes menuIcon: Int, action: () -> Unit) {
        headerMenuFirstButton.makeVisible()
        headerMenuFirstButton.setImageResource(menuIcon)
        headerMenuFirstButton.setOnClickListener { action() }
    }

    fun setMenuSecondButton(@DrawableRes menuIcon: Int, action: () -> Unit) {
        headerMenuSecondButton.makeVisible()
        headerMenuSecondButton.setImageResource(menuIcon)
        headerMenuSecondButton.setOnClickListener { action() }
    }

    fun setHeaderOfCard() {
        setTopPadding(0)
    }
}