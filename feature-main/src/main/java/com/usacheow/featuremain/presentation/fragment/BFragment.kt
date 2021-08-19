package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.TextSource.Simple
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.featuremain.R
import com.usacheow.featuremain.databinding.FragmentBBinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import com.usacheow.featuremain.presentation.viewmodels.BViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BFragment : SimpleFragment<FragmentBBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentBBinding::inflate,
    )

    @Inject lateinit var router: MainFeatureRouter

    private val aViewModel by hiltNavGraphViewModels<AViewModel>(R.id.main_nav_graph)
    private val bViewModel by viewModels<BViewModel>()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "B Fragment ${aViewModel.x} ${bViewModel.itemNumber}"
            setNavigationAction(R.drawable.ic_back) { router.moveToBack() }
        }

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = ViewTypesAdapter(
            listOf(
                ListTileItem(value = TextSource.Simple("1 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("2 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("3 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("4 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("5 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("6 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("7 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("8 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("9 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("10 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("11 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("12 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("13 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("14 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("15 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("16 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("17 Go to next screen"), clickListener = ::openNextScreen),
                ListTileItem(value = TextSource.Simple("18 Go to next screen"), clickListener = ::openNextScreen)
            )
        )
    }

    private fun openNextScreen() {
    }
}