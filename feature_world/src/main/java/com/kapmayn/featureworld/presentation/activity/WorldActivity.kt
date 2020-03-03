package com.kapmayn.featureworld.presentation.activity

import android.content.Context
import android.os.Bundle
import com.kapmayn.coreuikit.activity.SimpleActivity
import com.kapmayn.coreuikit.utils.start
import com.kapmayn.coreuikit.viewmodels.ViewModelFactory
import com.kapmayn.coreuikit.viewmodels.injectViewModel
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featureworld.R
import com.kapmayn.featureworld.di.WorldComponent
import com.kapmayn.featureworld.presentation.router.WorldFeatureRouter
import com.kapmayn.featureworld.presentation.viewmodels.WorldViewModel
import kotlinx.android.synthetic.main.activity_world.worldButton
import javax.inject.Inject

class WorldActivity : SimpleActivity() {

    @Inject lateinit var router: WorldFeatureRouter
    @Inject lateinit var viewModelFactory: ViewModelFactory
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
