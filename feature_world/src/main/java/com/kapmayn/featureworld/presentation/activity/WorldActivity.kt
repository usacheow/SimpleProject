package com.kapmayn.featureworld.presentation.activity

import android.content.Context
import android.os.Bundle
import com.kapmayn.core.presentation.activity.MvvmActivity
import com.kapmayn.core.utils.injectViewModel
import com.kapmayn.core.utils.start
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featureworld.R
import com.kapmayn.featureworld.di.WorldComponent
import com.kapmayn.featureworld.presentation.router.WorldFeatureRouter
import com.kapmayn.featureworld.presentation.viewmodels.WorldViewModel
import kotlinx.android.synthetic.main.activity_world.worldButton

class WorldActivity : MvvmActivity<WorldFeatureRouter>() {

    val viewModel by lazy { injectViewModel<WorldViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_world

    companion object {
        fun start(context: Context) {
            context.start<WorldActivity>()
        }
    }

    override fun inject(diProvider: DiProvider) {
        WorldComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        worldButton.setOnClickListener { router.moveToBack(this) }
    }
}
