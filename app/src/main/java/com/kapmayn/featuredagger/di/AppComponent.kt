package com.kapmayn.featuredagger.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.core.di.scope.ApplicationScope
import com.kapmayn.featuredagger.BaseApp
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent : CoreProvider {

    companion object {

        fun init(app: BaseApp): AppComponent {
            val appModule = AppModule(app)

            return DaggerAppComponent
                .builder()
                .appModule(appModule)
                .build()
        }
    }

    fun inject(app: BaseApp)
}