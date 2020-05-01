package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import com.usacheow.coreuikit.activity.SimpleActivity
import com.usacheow.coreuikit.utils.MultiStackHistoryManager
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.example.ExampleContainerFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import kotlinx.android.synthetic.main.activity_bottom_bar_main_screen.appBottomBar
import javax.inject.Inject

class BottomBarMainScreenActivity : SimpleActivity(), MultiStackHistoryManager.OnSelectedSectionChangedListener {

    override val layoutId = R.layout.activity_bottom_bar_main_screen

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { injectViewModel<StateViewModel>(viewModelFactory) }

    private val manager = MultiStackHistoryManager(
        supportFragmentManager,
        R.id.appContainerLayout,
        { ExampleContainerFragment.newInstance() },
        { ExampleContainerFragment.newInstance() },
        { ExampleContainerFragment.newInstance() }
    )

    override fun inject(diProvider: DiProvider) {
        MainScreenComponent.init(diProvider).inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.state = manager.getState()
        super.onSaveInstanceState(outState)
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

    override fun onBackPressed() {
        manager.backSection()
    }

    override fun selectSection(sectionNumber: Int) {
        manager.openSection(sectionNumber)

        appBottomBar.selectedItemId = AppScreenSections[sectionNumber]
    }

    override fun closeScreen() {
        finish()
    }
}

private val AppScreenSections = listOf(
    R.id.action_example_1,
    R.id.action_example_2,
    R.id.action_example_3
)