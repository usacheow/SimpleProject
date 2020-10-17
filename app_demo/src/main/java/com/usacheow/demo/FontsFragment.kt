package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.utils.view.PaddingValue
import kotlinx.android.synthetic.main.fragment_fonts.fontsListView
import kotlinx.android.synthetic.main.fragment_views.*

class FontsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_fonts

    companion object {
        fun newInstance() = FontsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        fontsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            title = "Fonts samples"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        fontsListView.setOnClickListener {

        }
    }
}