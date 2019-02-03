package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.diproviders.DiApp
import com.kapmayn.diproviders.DiProvider
import com.kapmayn.featuredagger.di.AppComponent

class FeatureDaggerApp : Application(), DiApp {

    override val diProvider: DiProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        (diProvider as AppComponent).inject(this)
    }
}