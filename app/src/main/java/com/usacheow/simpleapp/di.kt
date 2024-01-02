package com.usacheow.simpleapp

import com.usacheow.corecommon.model.BuildInfo
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.featureexample.presentation.ExampleFeatureProviderImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val appDiModule by DI.Module {
    importOnce(appViewModelDiModule)
    importOnce(buildInfoDiModule)
    importOnce(featureProvidersModule)
}

val appViewModelDiModule by DI.Module {
    bindProvider { AppViewModel(instance()) }
}

val buildInfoDiModule by DI.Module {
    bindSingleton {
        BuildInfo(
            applicationId = BuildConfig.APPLICATION_ID,
            buildType = BuildConfig.BUILD_TYPE,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME,
        )
    }
}

val featureProvidersModule by DI.Module {
    bindSingleton<ExampleFeatureProvider> { ExampleFeatureProviderImpl() }
}