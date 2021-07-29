package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.featuremain.R
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AFragment : SimpleFragment<FragmentABinding>() {

    @Inject
    lateinit var router: MainFeatureRouter

    override val params = Params(
        viewBindingProvider = FragmentABinding::inflate,
    )

    private val viewModel by hiltNavGraphViewModels<AViewModel>(R.id.main_nav_graph)

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.title = "A Fragment ${viewModel.x}"

        binding.listView.layoutManager = LinearLayoutManager(context)
        binding.listView.adapter = ViewTypesAdapter(
            List(20) {
                ListTileItem(value = TextString("$it Go to next screen"), clickListener = { openNextScreen(it) })
            }
        )
    }

    private fun openNextScreen(itemNumber: Int) {
        viewModel.x++
        router.fromAtoBScreen(itemNumber)
    }
}