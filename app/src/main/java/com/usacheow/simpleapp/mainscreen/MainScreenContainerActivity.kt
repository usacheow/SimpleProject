package com.usacheow.simpleapp.mainscreen

import com.usacheow.coreuikit.activity.ContainerActivity
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.mainscreen.example.ExampleContainerFragment

class MainScreenContainerActivity : ContainerActivity() {

    override val INIT_FRAGMENT_TAG_KEY = MainScreenContainerActivity::class.java.simpleName

    //    override fun getInitFragment() = TestContainerFragment.newInstance()
    override fun getInitFragment() = ExampleContainerFragment.newInstance()

    override fun inject(diProvider: DiProvider) {}
}