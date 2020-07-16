package com.usacheow.demo

import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.ext.PaddingValue
import kotlinx.android.synthetic.main.fragment_fonts.fontsListView

class FontsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_fonts

    companion object {
        fun newInstance() = FontsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        fontsListView.updatePadding(
            top = insets.systemWindowInsetTop,
            bottom = insets.systemWindowInsetBottom
        )
    }
}