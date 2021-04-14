package com.usacheow.simpleapp.mainscreen

import android.graphics.Rect
import android.os.Bundle
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import com.usacheow.coreui.utils.textinput.hideKeyboard
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
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

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.state = manager.getState()
        super.onSaveInstanceState(outState)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.appBottomBar.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )

        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - binding.appBottomBar.height, 0),
        )
        val i = WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
        return i
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        viewModel.state?.let { manager.setState(it) }
        manager.listener = this
        manager.openActiveSection()

        binding.appContainerLayout.doOnApplyWindowInsets { insets, padding ->
            insets
        }
        binding.appBottomBar.doOnApplyWindowInsets { insets, padding ->
            insets
        }

        binding.appBottomBar.setOnNavigationItemReselectedListener {
            binding.root.hideKeyboard()
            manager.resetSection()
        }
        binding.appBottomBar.setOnNavigationItemSelectedListener { menuItem ->
            binding.root.hideKeyboard()
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