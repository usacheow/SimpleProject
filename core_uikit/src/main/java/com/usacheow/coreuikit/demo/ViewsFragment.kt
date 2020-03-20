package com.usacheow.coreuikit.demo

import android.os.Bundle
import android.text.TextWatcher
import androidx.core.view.updatePadding
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.addCurrencyFormatter
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.coreuikit.views.Filter
import com.usacheow.coreuikit.views.SectionButton
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_views.chipsLayout
import kotlinx.android.synthetic.main.fragment_views.sectionButton
import kotlinx.android.synthetic.main.fragment_views.viewAmountInput
import kotlinx.android.synthetic.main.fragment_views.viewsRootView
import kotlinx.android.synthetic.main.fragment_views.viewsScrollView
import kotlinx.android.synthetic.main.fragment_views.viewsToolbar
import kotlinx.android.synthetic.main.simple_toolbar.toolbar

class ViewsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_views

    private var textWatcher: TextWatcher? = null

    companion object {
        fun newInstance() = ViewsFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        viewsRootView.doOnApplyWindowInsets { insets, _ ->
            viewsToolbar.updatePadding(top = insets.systemWindowInsetTop)
            viewsScrollView.updatePadding(bottom = insets.systemWindowInsetBottom)
        }

        toolbar.title = "Simple Toolbar"
        viewsToolbar.setBackgroundColor(color(R.color.colorDivider))

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