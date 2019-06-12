package com.kapmayn.featuredagger

import android.os.Bundle
import com.kapmayn.core.presentation.BottomBarHistoryManager
import com.kapmayn.core.presentation.activity.SimpleActivity
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featuredagger.di.AppComponent

class AppActivity : SimpleActivity() {

    override val layoutId = R.layout.activity_app

    private val manager = BottomBarHistoryManager(
        supportFragmentManager,
        R.id.appContainerLayout
    )

    override fun inject(diProvider: DiProvider) {
        AppComponent.init(application as FeatureDaggerApp).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
//        manager.openSection(0)
    }
}