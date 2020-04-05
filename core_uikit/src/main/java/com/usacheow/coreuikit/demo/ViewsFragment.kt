package com.usacheow.coreuikit.demo

import android.os.Bundle
import android.text.TextWatcher
import android.view.WindowInsets
import androidx.core.view.updatePadding
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.addCurrencyFormatter
import com.usacheow.coreuikit.views.Filter
import com.usacheow.coreuikit.views.SectionButton
import com.usacheow.coreuikit.views.SimpleAppBarLayout
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_views.chipsLayout
import kotlinx.android.synthetic.main.fragment_views.header
import kotlinx.android.synthetic.main.fragment_views.sectionButton
import kotlinx.android.synthetic.main.fragment_views.viewAmountInput
import kotlinx.android.synthetic.main.fragment_views.viewsScrollView

class ViewsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_views

    private var textWatcher: TextWatcher? = null

    companion object {
        fun newInstance() = ViewsFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun onApplyWindowInsets(insets: WindowInsets, padding: PaddingValue) {
        (header as SimpleAppBarLayout).setInset(insets.systemWindowInsetTop)
        viewsScrollView.updatePadding(bottom = insets.systemWindowInsetBottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            setBackground(R.color.colorDivider)
            title = "Simple Toolbar"
        }

        (sectionButton as SectionButton).populate(listOf(
            "One",
            "Two",
            "Three"
        ))

        textWatcher = viewAmountInput.addCurrencyFormatter("50000.00")

        chipsLayout.populate(setOf(
            Filter(1, "Chip 1", true),
            Filter(2, "Chip 2", false),
            Filter(3, "Chip 3", false),
            Filter(4, "Chip 4", false),
            Filter(5, "Chip 5", false)
        )) { _, _ -> }
    }

    override fun clearViews() {
        viewAmountInput.removeTextChangedListener(textWatcher)
    }
}