package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featuremain.presentation.fragment.container.HelloContainerFragment
import com.usacheow.featuremain.presentation.fragment.container.MockContainerFragment
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>(), MultiStackHistoryManager.OnSectionChangedListener {

    override val params = Params(
        viewBindingProvider = FragmentBottomBarBinding::inflate,
    )

    private val viewModel by viewModels<BottomBarViewModel>()

    private val manager by lazy {
        MultiStackHistoryManager(
            childFragmentManager,
            R.id.appContainerLayout,
            { HelloContainerFragment.newInstance() },
            { MockContainerFragment.newInstance() },
            { MockContainerFragment.newInstance() }
        )
    }

    companion object {
        fun newInstance() = BottomBarFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.state = manager.getState()
        super.onSaveInstanceState(outState)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.appBottomBar.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        viewModel.state?.let { manager.setState(it) }
        manager.listener = this
        manager.openActiveSection()

        binding.appBottomBar.setOnNavigationItemReselectedListener { manager.resetSection() }
        binding.appBottomBar.setOnNavigationItemSelectedListener { menuItem ->
            val position = AppScreenSections.indexOf(menuItem.itemId)
            manager.openSection(position)
            true
        }
    }

    override fun onBackPressed() = manager.backSection()

    override fun onSectionChanged(sectionNumber: Int) {
        binding.appBottomBar.selectedItemId = AppScreenSections[sectionNumber]
    }
}

private val AppScreenSections = listOf(
    R.id.action_example_1,
    R.id.action_example_2,
    R.id.action_example_3
)