package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.featuremain.R as FeatureR
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AFragment : SimpleFragment<FragmentABinding>() {

    @Inject
    lateinit var router: MainFeatureRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentABinding::inflate,
    )

    private val viewModel by hiltNavGraphViewModels<AViewModel>(FeatureR.id.main_nav_graph)
    private val adapter = ViewTypesAdapter()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.listView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.title = "A Fragment ${viewModel.x}"

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = adapter
        adapter.update(List(20) {
            ListTileItem(value = TextSource.Simple("$it Go to next screen"), clickListener = { openNextScreen(it) })
        })
    }

    private fun openNextScreen(itemNumber: Int) {
        viewModel.x++
        router.fromAtoBScreen(itemNumber)
//        adapter.update(
//            ListTileItem(value = TextSource.Simple("New Element"), clickListener = { openNextScreen(itemNumber) }),
//            itemNumber,
//        )
    }
}