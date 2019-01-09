package com.kapmayn.featureworld.activity

import android.content.Context
import android.os.Bundle
import com.kapmayn.core.di.CoreProvider
import com.kapmayn.coreui.activity.MvvmActivity
import com.kapmayn.coreui.utils.injectViewModel
import com.kapmayn.coreui.utils.start
import com.kapmayn.featureworld.R
import com.kapmayn.featureworld.di.WorldComponent
import com.kapmayn.featureworld.router.WorldFeatureRouter
import com.kapmayn.viewmodels.WorldViewModel
import kotlinx.android.synthetic.main.activity_world.worldButton

class WorldActivity : MvvmActivity<WorldFeatureRouter>() {

    val viewModel by lazy { injectViewModel<WorldViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_world

    companion object {
        fun start(context: Context) {
            context.start<WorldActivity>()
        }
    }

    override fun inject(coreProvider: CoreProvider) {
        WorldComponent.init(coreProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        worldButton.setOnClickListener { router.moveToBack(this) }
    }
}
