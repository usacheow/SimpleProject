package com.usacheow.simpleapp

import android.app.Application
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.usacheow.basesources.NetworkStateSource
import com.usacheow.coredata.coroutine.ApplicationCoroutineScopeHolder
import com.usacheow.corenavigation.AppRoute
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.LocalStringHolder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), ApplicationCoroutineScopeHolder, ImageLoaderFactory {

    @Inject
    lateinit var networkStateSource: NetworkStateSource

    @Inject
    lateinit var exampleFeatureProvider: ExampleFeatureProvider

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

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
@AndroidEntryPoint
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
            val windowSizeClass = calculateWindowSizeClass(this)
            SideEffect {
                shouldKeepSplashScreen = false
            }
            Navigator(AppScreen(windowSizeClass))
        }
    }
}

class AppScreen(private val windowSizeClass: WindowSizeClass) : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<AppViewModel>()
        val currentFlow by viewModel.currentFlowState.collectAsState()
        val stringHolder by viewModel.stringHolderState.collectAsState()

        AppTheme(windowSizeClass) {
            SystemBarsIconsColor()

            CompositionLocalProvider(LocalStringHolder provides stringHolder) {
                val route = when (currentFlow) {
                    AppViewModel.CurrentFlow.Main -> AppRoute.ExampleFirst
                }
                navigator.replaceAll(rememberScreen(route))
            }
        }
    }
}