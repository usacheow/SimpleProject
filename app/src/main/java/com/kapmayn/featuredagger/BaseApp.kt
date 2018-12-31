package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.DaggerApp
import com.kapmayn.featuredagger.di.AppComponent

class BaseApp : Application(), DaggerApp {

    override val baseProvider: BaseProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        (baseProvider as AppComponent).inject(this)
    }
}