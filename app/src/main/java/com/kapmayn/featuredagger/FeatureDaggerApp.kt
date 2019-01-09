package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.core.CoreApp
import com.kapmayn.core.di.CoreProvider
import com.kapmayn.featuredagger.di.AppComponent

class FeatureDaggerApp : Application(), CoreApp {

    override val coreProvider: CoreProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        (coreProvider as AppComponent).inject(this)
    }
}