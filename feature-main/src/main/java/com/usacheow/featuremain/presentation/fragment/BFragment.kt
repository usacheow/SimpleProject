package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.navigation.addArgs
import com.usacheow.coreui.uikit.helper.startFragmentTransition
import com.usacheow.featuremain.databinding.FragmentBBinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import com.usacheow.featuremain.presentation.viewmodels.BViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.featuremain.R as FeatureR

@AndroidEntryPoint
class BFragment : SimpleFragment<FragmentBBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentBBinding::inflate,
    )

    @Inject lateinit var router: MainFeatureRouter

    private val aViewModel by hiltNavGraphViewModels<AViewModel>(FeatureR.id.main_nav_graph)
    private val bViewModel by viewModels<BViewModel>()

    companion object {
        fun bundle(itemNumber: Int) = Bundle().addArgs(BViewModel.Args(itemNumber))
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.listView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.title = "B Fragment ${aViewModel.x} ${bViewModel.itemNumber}"
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.listView.layoutManager = LinearLayoutManager(context)
        startFragmentTransition {
            binding.listView.adapter = ViewStateAdapter(
                List(20) {
                    ListTileItem(value = TextSource.Simple("${it + 1} Go to next screen"), clickListener = { openNextScreen() })
                }
            )
        }
    }

    private fun openNextScreen() {
    }
}