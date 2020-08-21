package com.usacheow.coreui.utils.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.utils.system.ifSupportLollipop

class AnimateAppBarOnScrollListener(
    private val manager: LinearLayoutManager,
    private val appBarLayout: AppBarLayout
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        ifSupportLollipop {
            val isFirstItemVisible = manager.findFirstCompletelyVisibleItemPosition() == 0
            val isAppBarDown = appBarLayout.elevation == 0f

            if (isFirstItemVisible && !isAppBarDown) {
                appBarLayout.animateElevation(true)
            } else if (!isFirstItemVisible && isAppBarDown) {
                appBarLayout.animateElevation(false, duration = 0)
            }
        }
    }
}