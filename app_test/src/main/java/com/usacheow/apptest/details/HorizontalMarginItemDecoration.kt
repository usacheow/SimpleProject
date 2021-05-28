package com.usacheow.apptest.details

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.utils.view.toPx

class HorizontalMarginItemDecoration(
    private val horizontalMarginInPx: Double
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx.toInt()
        outRect.left = horizontalMarginInPx.toInt()
    }

}