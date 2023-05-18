package com.usacheow.simpleapp

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.usacheow.basesources.baseSourcesDiModule
import com.usacheow.coredata.coreDataDiModule
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.coreui.coreUiDiModule
import com.usacheow.featureexample.featureExampleDiModule
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.compose.withDI
import org.kodein.di.instance

class AndroidApp : Application(), ImageLoaderFactory {

    val kodein = DI {
        bindSingleton<Context> { this@AndroidApp }
        import(coreUiDiModule)
        import(coreDataDiModule)

        import(baseSourcesDiModule)

        import(appDiModule)
        import(featureExampleDiModule)
    }

//    private val networkStateSource by kodein.instance<NetworkStateSource>()
    private val exampleFeatureProvider by kodein.instance<ExampleFeatureProvider>()

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            exampleFeatureProvider.exampleGraph.invoke(this)
        }
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .components {
            add(SvgDecoder.Factory())
        }
        .crossfade(true)
        .build()
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class AppActivity : FragmentActivity() {

    private var shouldKeepSplashScreen = true

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().setKeepOnScreenCondition {
            shouldKeepSplashScreen
        }

        setContent {
            withDI((application as AndroidApp).kodein) {
                val windowSizeClass = calculateWindowSizeClass(this)
                SideEffect {
                    shouldKeepSplashScreen = false
                }
                Navigator(App(windowSizeClass))
            }
        }
    }
}