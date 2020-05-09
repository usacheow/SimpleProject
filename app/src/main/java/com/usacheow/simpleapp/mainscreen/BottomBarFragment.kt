package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.MultiStackHistoryManager
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.presentation.fragment.HelloContainerFragment
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.example.ExampleContainerFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import kotlinx.android.synthetic.main.fragment_bottom_bar.appBottomBar
import javax.inject.Inject

class BottomBarFragment : SimpleFragment(), MultiStackHistoryManager.OnSectionChangedListener {

    override val layoutId = R.layout.fragment_bottom_bar

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by injectViewModel<BottomBarViewModel> { viewModelFactory }

    private val manager by lazy {
        MultiStackHistoryManager(
            childFragmentManager,
            R.id.appContainerLayout,
            { HelloContainerFragment.newInstance() },
            { ExampleContainerFragment.newInstance() },
            { HelloContainerFragment.newInstance() }
        )
    }

    companion object {
        fun newInstance() = BottomBarFragment()
    }

    override fun inject(diProvider: DiProvider) {
        MainScreenComponent.init(diProvider).inject(this)
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