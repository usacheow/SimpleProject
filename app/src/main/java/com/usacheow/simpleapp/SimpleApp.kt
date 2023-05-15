package com.usacheow.simpleapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.usacheow.basesources.NetworkStateSource
import com.usacheow.coredata.coroutine.ApplicationCoroutineScopeHolder
import com.usacheow.coredata.network.ApiConfig
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder, ImageLoaderFactory {

    @Inject lateinit var networkStateSource: NetworkStateSource

    @Inject lateinit var apiConfig: ApiConfig

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun newImageLoader() = ImageLoader.Builder(this)
        .okHttpClient {
            apiConfig.okHttpBuilder()
                .build()
        }
        .components {
            add(SvgDecoder.Factory())
        }
        .crossfade(true)
        .build()
}