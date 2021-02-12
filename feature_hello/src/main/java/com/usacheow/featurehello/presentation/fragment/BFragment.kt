package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featurehello.R
import com.usacheow.featurehello.databinding.FragmentBBinding
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BFragment : SimpleFragment<FragmentBBinding>() {

    private val aViewModel by viewModels<AViewModel>({ requireParentFragment() })
    private val bViewModel by viewModels<BViewModel>()

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBBinding {
        return FragmentBBinding.inflate(inflater, container, false)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.listView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        bViewModel.x++
        binding.header.root.apply {
            title = "B Fragment ${aViewModel.x} ${bViewModel.x}"
            setBackground(R.color.colorGreyCard)
            setNavigationAction(R.drawable.ic_back) { activity?.onBackPressed() }
        }

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = ViewTypesAdapter(listOf(
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