package com.usacheow.featurehello.presentation.fragment

import android.app.Application
import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.utils.ext.PaddingValue
import com.usacheow.coreui.utils.ext.toPx
import com.usacheow.coreui.viewmodels.ViewModelFactory
import com.usacheow.di.DiApp
import com.usacheow.featurehello.R
import com.usacheow.featurehello.di.HelloComponent
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import kotlinx.android.synthetic.main.fragment_b.header
import kotlinx.android.synthetic.main.fragment_b.listView
import javax.inject.Inject

class BFragment : SimpleFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val aViewModel by viewModels<AViewModel>({ requireParentFragment() }, { viewModelFactory })
    private val bViewModel by viewModels<BViewModel> { viewModelFactory }

    override val layoutId = R.layout.fragment_b

    override fun inject(application: Application) {
        HelloComponent.init((application as DiApp).diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        listView.updatePadding(bottom = insets.systemWindowInsetBottom + 56.toPx)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        bViewModel.x++
        (header as SimpleAppBarLayout).apply {
            title = "B Fragment ${aViewModel.x} ${bViewModel.x}"
            setBackground(R.color.colorGreyCard)
            setNavigationAction(R.drawable.ic_back) { activity?.onBackPressed() }
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
//        getContainer { show() }
    }
}