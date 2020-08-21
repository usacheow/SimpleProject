package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx
import kotlinx.android.synthetic.main.fragment_example.calendarScreen
import kotlinx.android.synthetic.main.fragment_example.cameraScreen
import kotlinx.android.synthetic.main.fragment_example.exampleHeaderView
import kotlinx.android.synthetic.main.fragment_example.fontsScreen
import kotlinx.android.synthetic.main.fragment_example.rootView
import kotlinx.android.synthetic.main.fragment_example.viewsScreen
import kotlinx.android.synthetic.main.fragment_example.widgetsScreen

class ExampleFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_example

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        exampleHeaderView.updatePadding(top = insets.systemWindowInsetTop + 16.toPx)
        rootView.updatePadding(bottom = insets.systemWindowInsetBottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        fontsScreen.doOnClick { show(FontsFragment.newInstance()) }
        widgetsScreen.doOnClick { show(WidgetsFragment.newInstance()) }
        viewsScreen.doOnClick { show(ViewsFragment.newInstance()) }
        calendarScreen.doOnClick { show(CalendarFragment.newInstance()) }
        cameraScreen.doOnClick { show(CameraFragment.newInstance()) }
    }

    private fun show(fragment: Fragment) {
        getContainer { show(fragment) }
    }
}