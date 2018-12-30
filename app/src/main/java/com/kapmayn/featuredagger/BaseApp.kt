package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.core.base.App
import com.kapmayn.core.di.CoreProvider
import com.kapmayn.featuredagger.di.AppComponent

class BaseApp : Application(), App {

    override val coreProvider: CoreProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        (coreProvider as AppComponent).inject(this)
    }
}