package com.usacheow.featuremain.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.withCreated
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible
import com.usacheow.featuremain.R
import com.usacheow.featuremain.databinding.FragmentABinding
import com.usacheow.featuremain.presentation.navigation.MainFeatureRouter
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import com.usacheow.featuremain.presentation.viewmodels.BViewModel
import com.usacheow.featuremain.presentation.viewmodels.CViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import javax.inject.Inject
import android.widget.EdgeEffect
import androidx.core.widget.EdgeEffectCompat

@AndroidEntryPoint
class AFragment : SimpleFragment<FragmentABinding>() {

    @Inject
    lateinit var router: MainFeatureRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentABinding::inflate,
    )

    private val viewModel by hiltNavGraphViewModels<AViewModel>(R.id.main_nav_graph)
    private val cViewModel by viewModels<CViewModel>()
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

    override fun subscribe() {
//        cViewModel.results.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//        }
    }

    private fun openNextScreen(itemNumber: Int) {
        if (itemNumber % 2 == 0) {
            cViewModel.setQuery("test_$itemNumber")
        } else {
            viewModel.x++
            router.fromAtoBScreen(itemNumber)
        }
//        adapter.update(
//            ListTileItem(value = TextSource.Simple("New Element"), clickListener = { openNextScreen(itemNumber) }),
//            itemNumber,
//        )
    }
}