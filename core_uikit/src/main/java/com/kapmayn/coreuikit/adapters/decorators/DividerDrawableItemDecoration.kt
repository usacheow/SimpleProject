package com.kapmayn.coreuikit.adapters.decorators

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerDrawableItemDecoration(
    context: Context,
    @DrawableRes private val divider: Int,
    private val shouldShowLastDivider: Boolean = false
) : RecyclerView.ItemDecoration() {

    private var dividerDrawable: Drawable = ContextCompat.getDrawable(context, divider)!!

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        var childCount = parent.childCount

        if (!shouldShowLastDivider) {
            childCount--
        }

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + dividerDrawable.intrinsicHeight

            dividerDrawable.setBounds(left, top, right, bottom)
            dividerDrawable.draw(canvas)
        }
    }
}