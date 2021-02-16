package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featurehello.R
import com.usacheow.featurehello.databinding.FragmentABinding
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AFragment : SimpleFragment<FragmentABinding>() {

    override val params = Params(
        viewBindingProvider = FragmentABinding::inflate,
    )

    private val viewModel by viewModels<AViewModel>({ requireParentFragment() })

    companion object {
        fun newInstance() = AFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.listView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "A Fragment ${viewModel.x}"
            setBackground(R.color.colorGreyCard)
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
        viewModel.x++
        getContainer { show(BFragment()) }

        childFragmentManager
        parentFragmentManager
    }
}