package com.kapmayn.featuredagger.di

import com.kapmayn.di.BaseProvider
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.featuredagger.BaseApp
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent : BaseProvider {

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