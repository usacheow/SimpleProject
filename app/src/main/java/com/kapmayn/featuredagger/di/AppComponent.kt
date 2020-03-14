package com.kapmayn.featuredagger.di

import com.kapmayn.core.provider.DiProvider
import com.kapmayn.coredata.di.CoreDataModule
import com.kapmayn.di.ApplicationScope
import com.kapmayn.featuredagger.AppActivity
import com.kapmayn.featuredagger.FeatureDaggerApp
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CoreModule::class,
        MediatorModule::class,
        CoreDataModule::class
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