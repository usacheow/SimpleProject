package com.usacheow.featurebottombar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.uikit.duplicate.BottomNavigationDefaults
import com.usacheow.coreuicompose.uikit.duplicate.LocalBottomNavigationHeight
import com.usacheow.coreuicompose.uikit.duplicate.SimpleBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarScreen(
    items: List<BottomBarFeatureProvider.ScreenItem>,
) {
    val navController = rememberNavController()
    val firstRootRoute = items.first().route.route

    Scaffold(
        bottomBar = { BottomBar(navController, items) },
    ) {
        CompositionLocalProvider(
            LocalBottomNavigationHeight provides BottomNavigationDefaults.Height,
        ) {
            NavHost(navController = navController, startDestination = firstRootRoute) {
                items.forEach {
                    with(it) {
                        builder(navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    items: List<BottomBarFeatureProvider.ScreenItem>,
) {
    SimpleBottomNavigation(
        contentPadding = getBottomInset(),
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                alwaysShowLabel = false,
                icon = { Icon(painterResource(screen.iconRes), contentDescription = null) },
                label = { Text(stringResource(screen.labelRes)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route.route } == true,
                onClick = { navController.switchTo(screen.route.route) }
            )
        }
    }
}

private fun NavController.switchTo(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}