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
import com.usacheow.coreui.uikit.helper.startFragmentTransition
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import com.usacheow.featuremain.presentation.viewmodels.CViewModel
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