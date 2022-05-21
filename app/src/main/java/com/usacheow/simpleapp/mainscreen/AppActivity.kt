package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.ScreenRoute
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.corenavigation.TabRoute
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreCommonR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class AppActivity : FragmentActivity() {

    @Inject
    lateinit var bottomBarFeatureProvider: BottomBarFeatureProvider

    @Inject
    lateinit var mainFeatureProvider: MainFeatureProvider

    @Inject
    lateinit var exampleFeatureProvider: ExampleFeatureProvider

    private var shouldKeepSplashScreen = true

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().setKeepOnScreenCondition {
            shouldKeepSplashScreen
        }

        setContent {
            AppTheme(calculateWindowSizeClass(this)) {
                SideEffect {
                    shouldKeepSplashScreen = false
                }
                SystemBarsIconsColor()
                AppScreen()
            }
        }
    }

    @Composable
    private fun AppScreen() {
        val navController = rememberNavController()
        val viewModel = viewModel<AppViewModel>()
        val currentFlow by viewModel.currentFlowState.collectAsState()

        when (currentFlow) {
            AppViewModel.CurrentFlow.Main -> MainFlow(navController)
        }
    }

    @Composable
    private fun MainFlow(navController: NavHostController) {
        NavHost(navController = navController, startDestination = ScreenRoute.mainBottomBar.route) {
            with(bottomBarFeatureProvider) { bottomBarGraph(ScreenRoute.mainBottomBar, mainBottomBarItems()) }
            with(exampleFeatureProvider) { exampleGraph(navController) }
        }
    }

    private fun mainBottomBarItems() = listOf(
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_logo,
            labelRes = CoreCommonR.string.bb_example,
            route = TabRoute.mainTab1,
            startDestination = ScreenRoute.mock1,
            builder = { mainTab1Graph(it) },
        ),
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_logo,
            labelRes = CoreCommonR.string.bb_example,
            route = TabRoute.mainTab2,
            startDestination = ScreenRoute.mock2,
            builder = { mainTab2Graph(it) },
        ),
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_logo,
            labelRes = CoreCommonR.string.bb_example,
            route = TabRoute.mainTab3,
            startDestination = ScreenRoute.mock3,
            builder = { mainTab3Graph(it) },
        ),
    )

    private fun NavGraphBuilder.mainTab1Graph(controller: NavHostController) {
        with(mainFeatureProvider) { mock1Graph(controller) }
    }

    private fun NavGraphBuilder.mainTab2Graph(controller: NavHostController) {
        with(mainFeatureProvider) { mock2Graph(controller) }
    }

    private fun NavGraphBuilder.mainTab3Graph(controller: NavHostController) {
        with(mainFeatureProvider) { mock3Graph(controller) }
    }
}