package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
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
        binding.header.root.applyInsets(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.title = "A Fragment ${viewModel.x}"

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = ViewTypesAdapter(listOf(
            ListTileItem(value = TextString("1 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("2 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("3 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("4 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("5 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("6 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("7 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("8 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("9 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("10 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("11 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("12 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("13 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("14 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("15 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("16 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("17 Go to next screen"), clickListener = ::openNextScreen),
            ListTileItem(value = TextString("18 Go to next screen"), clickListener = ::openNextScreen)
        ))
    }

    private fun openNextScreen() {
        viewModel.x++
        getContainer { navigateTo(BFragment()) }

        childFragmentManager
        parentFragmentManager
    }
}