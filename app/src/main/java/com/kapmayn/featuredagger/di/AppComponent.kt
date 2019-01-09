package com.kapmayn.featuredagger.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.di.BaseModule
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.featuredagger.FeatureDaggerApp
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        BaseModule::class,
        FeatureApiModule::class
    ]
)
interface AppComponent : CoreProvider {

    companion object {

        fun init(app: FeatureDaggerApp): AppComponent {
            val baseModule = BaseModule(app)

            return DaggerAppComponent
                .builder()
                .baseModule(baseModule)
                .build()
        }
    }

    fun inject(app: FeatureDaggerApp)
}