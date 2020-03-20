package com.usacheow.coreuikit.demo

import android.os.Bundle
import androidx.core.view.updatePadding
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_fonts.fontsListView

class FontsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_fonts

    companion object {
        fun newInstance() = FontsFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        fontsListView.doOnApplyWindowInsets { insets, _ ->
            fontsListView.updatePadding(top = insets.systemWindowInsetTop, bottom = insets.systemWindowInsetBottom)
        }
    }
}