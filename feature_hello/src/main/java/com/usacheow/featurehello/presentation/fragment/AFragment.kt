package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.TextString
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
//        binding.listView.updatePadding(
//            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
//        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "A Fragment ${viewModel.x}"
            setBackground(R.color.surfaceSecondary)
        }

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = ViewTypesAdapter(listOf(
            ListTileItem(title = TextString("1 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("2 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("3 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("4 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("5 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("6 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("7 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("8 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("9 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("10 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("11 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("12 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("13 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("14 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("15 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("16 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("17 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(title = TextString("18 Go to next screen"), clickListener = ::openNextScreen)
        ))
    }

    private fun openNextScreen() {
        viewModel.x++
        getContainer { navigateTo(BFragment()) }

        childFragmentManager
        parentFragmentManager
    }
}