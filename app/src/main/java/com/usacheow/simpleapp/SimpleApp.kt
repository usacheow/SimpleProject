package com.usacheow.simpleapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.usacheow.basesources.NetworkStateSource
import com.usacheow.coredata.coroutine.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder, ImageLoaderFactory {

    @Inject lateinit var networkStateSource: NetworkStateSource

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun newImageLoader() = ImageLoader.Builder(this)
        .components {
            add(SvgDecoder.Factory())
        }
        .crossfade(true)
        .build()
}