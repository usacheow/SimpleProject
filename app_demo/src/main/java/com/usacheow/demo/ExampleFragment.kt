package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.android.synthetic.main.fragment_example.header
import kotlinx.android.synthetic.main.fragment_views.*

class ExampleFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_example

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        listView.updatePadding(bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            title = "Demo UiKit"
        }

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