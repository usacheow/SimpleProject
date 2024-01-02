package com.usacheow.showcaseapp

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.usacheow.coredata.coreDataCoroutinesDiModule
import com.usacheow.coredata.storage.preferences.PreferencesProvider
import com.usacheow.coredata.storage.preferences.ThemeMode
import com.usacheow.coredata.storage.preferences.TokenStorage
import com.usacheow.coredata.storage.preferences.UserDataStorage
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.showcaseapp.catalog.BottomBarScreen
import com.usacheow.showcaseapp.catalog.BottomSheetScreen
import com.usacheow.showcaseapp.catalog.ButtonsScreen
import com.usacheow.showcaseapp.catalog.CellTilesScreen
import com.usacheow.showcaseapp.catalog.DemoScreen
import com.usacheow.showcaseapp.catalog.InformationTilesScreen
import com.usacheow.showcaseapp.catalog.InputsScreen
import com.usacheow.showcaseapp.catalog.MessageScreen
import com.usacheow.showcaseapp.catalog.ModalBottomSheetScreen
import com.usacheow.showcaseapp.catalog.NumPadScreen
import com.usacheow.showcaseapp.catalog.PaletteScreen
import com.usacheow.showcaseapp.catalog.TagListScreen
import com.usacheow.showcaseapp.catalog.TypographyScreen
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.compose.withDI
import org.kodein.di.instance

class ShowcaseApp : Application() {

    val kodein = DI {
        bindSingleton<Context> { this@ShowcaseApp }
        bindSingleton { PreferencesProvider(instance()) }
        bindSingleton { UserDataStorage(instance()) }
        importOnce(coreDataCoroutinesDiModule)
    }

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            register<DemoScreens.Demo> { DemoScreen() }
            register<DemoScreens.Settings> { SettingsScreen() }
            register<DemoScreens.Typography> { TypographyScreen() }
            register<DemoScreens.Buttons> { ButtonsScreen() }
            register<DemoScreens.Inputs> { InputsScreen() }
            register<DemoScreens.CellTiles> { CellTilesScreen() }
            register<DemoScreens.InformationTiles> { InformationTilesScreen() }
            register<DemoScreens.Messages> { MessageScreen() }
            register<DemoScreens.NumPad> { NumPadScreen() }
            register<DemoScreens.Palette> { PaletteScreen() }
            register<DemoScreens.TagList> { TagListScreen() }
            register<DemoScreens.BottomSheet> { BottomSheetScreen() }
            register<DemoScreens.ModalBottomSheet> { ModalBottomSheetScreen() }
            register<DemoScreens.BottomBar> { BottomBarScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class ShowcaseActivity : FragmentActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Content()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun Content() {
        withDI((application as ShowcaseApp).kodein) {
            val userDataStorage: UserDataStorage by (application as ShowcaseApp).kodein.instance()
            val mode = userDataStorage.themeModeFlow.collectAsState(initial = ThemeMode.System)
            val isDarkTheme = when (mode.value) {
                ThemeMode.System -> isSystemInDarkTheme()
                ThemeMode.Dark -> true
                ThemeMode.Light -> false
            }
            AppTheme(calculateWindowSizeClass(this), isDarkTheme = isDarkTheme) {
                val demoScreen = rememberScreen(DemoScreens.Demo)

                SystemBarsIconsColor(needWhiteAllIcons = isDarkTheme)
                Navigator(demoScreen)
            }
        }
    }
}

sealed class DemoScreens : ScreenProvider {
    object Demo : DemoScreens()
    object Settings : DemoScreens()
    object Typography : DemoScreens()
    object Buttons : DemoScreens()
    object Inputs : DemoScreens()
    object CellTiles : DemoScreens()
    object InformationTiles : DemoScreens()
    object Messages : DemoScreens()
    object NumPad : DemoScreens()
    object Palette : DemoScreens()
    object TagList : DemoScreens()
    object BottomSheet : DemoScreens()
    object ModalBottomSheet : DemoScreens()
    object BottomBar : DemoScreens()
}

const val DeeplinkDemo = "app://com.usacheow.appdemo/demo"