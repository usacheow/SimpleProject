package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.navigation.MultiStackHistoryManager
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featurehello.presentation.fragment.HelloContainerFragment
import com.usacheow.simpleapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bottom_bar.appBottomBar

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment(), MultiStackHistoryManager.OnSectionChangedListener {

    override val layoutId = R.layout.fragment_bottom_bar

    private val viewModel by viewModels<BottomBarViewModel>()

    private val manager by lazy {
        MultiStackHistoryManager(
            childFragmentManager,
            R.id.appContainerLayout,
            { HelloContainerFragment.newInstance() },
            { HelloContainerFragment.newInstance() },
            { HelloContainerFragment.newInstance() }
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
        appBottomBar.updatePadding(bottom = insets.systemWindowInsetBottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        viewModel.state?.let { manager.setState(it) }
        manager.listener = this
        manager.openActiveSection()

        appBottomBar.setOnNavigationItemReselectedListener { manager.resetSection() }
        appBottomBar.setOnNavigationItemSelectedListener { menuItem ->
            val position = AppScreenSections.indexOf(menuItem.itemId)
            manager.openSection(position)
            true
        }
    }

    override fun onBackPressed() = manager.backSection()

    override fun onSectionChanged(sectionNumber: Int) {
        appBottomBar.selectedItemId = AppScreenSections[sectionNumber]
    }
}

private val AppScreenSections = listOf(
    R.id.action_example_1,
    R.id.action_example_2,
    R.id.action_example_3
)