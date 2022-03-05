package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.tools.startFragmentTransition
import com.usacheow.coreuiview.uikit.molecule.ListTileItem
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.featuremain.R as FeatureR

@AndroidEntryPoint
class AFragment : SimpleFragment<FragmentABinding>() {

    @Inject lateinit var router: MainFeatureRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentABinding::inflate,
    )

    private val viewModel by hiltNavGraphViewModels<AViewModel>(FeatureR.id.main_nav_graph)

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.listView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.title = "A Fragment ${viewModel.x}"

        binding.listView.layoutManager = LinearLayoutManager(context)
        startFragmentTransition {
            binding.listView.adapter = ViewStateAdapter(
                List(20) {
                    ListTileItem(value = TextSource.Simple("${it + 1} Go to next screen"), clickListener = { openNextScreen(it) })
                }
            )
        }
    }

    private fun openNextScreen(itemNumber: Int) {
        viewModel.x++
        router.fromAtoBScreen(itemNumber)
    }
}