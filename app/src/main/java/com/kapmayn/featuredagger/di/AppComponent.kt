package com.kapmayn.featuredagger.di

import com.kapmayn.database.di.DataBaseModule
import com.kapmayn.datanetwork.di.NetworkModule
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.diproviders.DiProvider
import com.kapmayn.featuredagger.AppActivity
import com.kapmayn.featuredagger.FeatureDaggerApp
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CoreModule::class,
        FeatureApiModule::class,
        NetworkModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent : DiProvider {

    companion object {

        fun init(app: FeatureDaggerApp): AppComponent {
            val coreModule = CoreModule(app)

            return DaggerAppComponent
                .builder()
                .coreModule(coreModule)
                .build()
        }
    }

    fun inject(app: FeatureDaggerApp)

    fun inject(app: AppActivity)
}