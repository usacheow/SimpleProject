package com.usacheow.featurebottombar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.coreuicompose.tools.LocalBottomNavigationHeight
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBarConfig
import com.usacheow.coreuitheme.compose.LocalStringHolder

@Composable
fun BottomBarScreen(
    items: List<BottomBarFeatureProvider.ScreenItem>,
) {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val firstDestRoutes = items.map { it.startDestination.path() }

    BackHandler(currentDestination?.route in firstDestRoutes.drop(1)) {
        navController.switchTo(items.first().route.pattern)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalBottomNavigationHeight provides 80.dp) {
            NavHost(navController = navController, startDestination = items.first().route.pattern) {
                items.forEach {
                    navigation(
                        route = it.route.pattern,
                        startDestination = it.startDestination.pattern,
                    ) {
                        with(it) { builder(navController) }
                    }
                }
            }
            NavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                containerColor = NavigationBarDefaults.containerColor.copy(alpha = SimpleTopAppBarConfig.ContainerColorAlpha),
            ) {
                items.forEach { screen ->
                    NavigationBarItem(
                        alwaysShowLabel = false,
                        icon = { Icon(screen.icon.get(), contentDescription = null) },
                        label = { Text(LocalStringHolder.current(screen.labelKey)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route.path() } == true,
                        onClick = { navController.switchTo(screen.route.path()) }
                    )
                }
            }
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