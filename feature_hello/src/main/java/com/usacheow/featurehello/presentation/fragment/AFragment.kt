package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreuikit.adapters.ViewTypesAdapter
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.toPx
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.views.SimpleAppBarLayout
import com.usacheow.coreuikit.widgets.ActionItem
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.R
import com.usacheow.featurehello.di.HelloComponent
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import kotlinx.android.synthetic.main.fragment_a.header
import kotlinx.android.synthetic.main.fragment_a.listView
import javax.inject.Inject

class AFragment : SimpleFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by injectViewModel<AViewModel>({ requireParentFragment() }, { viewModelFactory })

    override val layoutId = R.layout.fragment_a

    companion object {
        fun newInstance() = AFragment()
    }

    override fun inject(diProvider: DiProvider) {
        HelloComponent.init(diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        listView.updatePadding(bottom = insets.systemWindowInsetBottom + 56.toPx)
    }

    override fun getSharedViews() = listOf(header)

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            title = "A Fragment ${viewModel.x}"
            setBackground(R.color.colorGreyCard)
        }

        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = ViewTypesAdapter(listOf(
            ActionItem(title = "1 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "2 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "3 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "4 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "5 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "6 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "7 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "8 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "9 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "10 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "11 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "12 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "13 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "14 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "15 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "16 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "17 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "18 Go to next screen", onItemClicked = ::openNextScreen)
        ))
    }

    private fun openNextScreen() {
        viewModel.x++
        getContainer { show(BFragment()) }
    }
}