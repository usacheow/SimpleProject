package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.FeatureRoute
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreCommonR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    @Inject lateinit var onBoardingFeatureProvider: OnBoardingFeatureProvider
    @Inject lateinit var mainFeatureProvider: MainFeatureProvider
    @Inject lateinit var bottomBarFeatureProvider: BottomBarFeatureProvider

    private val startDestination = FeatureRoute.onBoarding

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<AppViewModel>()

                viewModel.initialScreenEvent.observe { value ->
                    val route = when(value) {
                        AppViewModel.Action.OpenAppScreen -> FeatureRoute.authZone
                        AppViewModel.Action.OpenOnBoardingScreen -> FeatureRoute.onBoarding
                    }
                    navController.navigate(route.route) {
                        popUpTo(startDestination.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                SystemBarsIconsColor()
                NavHost(navController)
            }
        }
    }

    @Composable
    private fun NavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = startDestination.route) {
            with(onBoardingFeatureProvider) {
                onBoardingGraph(FeatureRoute.onBoarding, navController)
            }
            with(bottomBarFeatureProvider) {
                bottomBarGraph(FeatureRoute.authZone, authZoneBottomBarItems())
            }
        }
    }

    private fun authZoneBottomBarItems() = listOf(
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_user,
            labelRes = CoreCommonR.string.bb_example,
            route = FeatureRoute.main,
            builder = { with(mainFeatureProvider) { mainGraph(FeatureRoute.main, it) } },
        ),
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_date,
            labelRes = CoreCommonR.string.bb_example,
            route = FeatureRoute.mock,
            builder = { with(mainFeatureProvider) { mockGraph(FeatureRoute.mock, it) } },
        ),
        BottomBarFeatureProvider.ScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_accept,
            labelRes = CoreCommonR.string.bb_example,
            route = FeatureRoute.mock2,
            builder = { with(mainFeatureProvider) { mock2Graph(FeatureRoute.mock2, it) } },
        ),
    )
}