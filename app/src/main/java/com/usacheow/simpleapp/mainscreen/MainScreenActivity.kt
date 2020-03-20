package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import com.usacheow.coreuikit.activity.SimpleActivity
import com.usacheow.coreuikit.utils.BottomBarHistoryManager
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.R
import javax.inject.Inject

class MainScreenActivity : SimpleActivity(), BottomBarHistoryManager.OnSelectedSectionChangedListener {

    override val layoutId = R.layout.activity_app

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { injectViewModel<MainScreenViewModel>(viewModelFactory) }

    private val manager = BottomBarHistoryManager(
        supportFragmentManager,
        R.id.appContainerLayout,
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
    }

    override fun onBackPressed() {
        manager.backSection()
    }

    override fun selectSection(sectionNumber: Int) {
    }

    override fun closeScreen() {
        finish()
    }
}