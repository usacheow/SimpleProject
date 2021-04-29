package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.featuremain.presentation.fragment.container.HelloContainerFragment
import com.usacheow.featuremain.presentation.fragment.container.MockContainerFragment
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>(),
    MultiStackHistoryManager.OnSectionChangedListener {

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

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.appBottomBar.updatePadding(bottom = insets.getBottomInset())

        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - binding.appBottomBar.height, 0),
        )
        return WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        viewModel.state?.let { manager.setState(it) }
        manager.listener = this
        manager.openActiveSection()

        binding.appBottomBar.doOnApplyWindowInsets { insets, _ -> insets }
        binding.appBottomBar.setOnNavigationItemReselectedListener {
            windowInsetsController?.hideIme()
            manager.resetSection()
        }
        binding.appBottomBar.setOnNavigationItemSelectedListener { menuItem ->
            windowInsetsController?.hideIme()
            val position = AppScreenSections.indexOf(menuItem.itemId)
            manager.openSection(position)
            true
        }
    }

    override fun onSectionChanged(sectionNumber: Int) {
        binding.appBottomBar.selectedItemId = AppScreenSections[sectionNumber]
    }

    override fun onBackPressed() = manager.backSection()

    override fun onDestroyView() {
        viewModel.state = manager.getState()
        super.onDestroyView()
    }
}

private val AppScreenSections = listOf(
    R.id.action_example_1,
    R.id.action_example_2,
    R.id.action_example_3
)