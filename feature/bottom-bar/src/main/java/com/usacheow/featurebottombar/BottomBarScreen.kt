package com.usacheow.featurebottombar

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

@Composable
fun BottomBarScreen(
    items: List<BottomBarFeatureProvider.ScreenItem>,
) {
    val navController = rememberNavController()
    val firstRootRoute = items.first().route

    Box {
        CompositionLocalProvider(LocalBottomNavigationHeight provides 80.dp) {
            NavHost(navController = navController, startDestination = firstRootRoute.pattern) {
                items.forEach {
                    navigation(
                        route = it.route.pattern,
                        startDestination = it.startDestination.pattern,
                    ) {
                        with(it) { builder(navController) }
                    }
                }
            }
        }
        BottomBar(modifier = Modifier.align(Alignment.BottomCenter), navController, items)
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier,
    navController: NavController,
    items: List<BottomBarFeatureProvider.ScreenItem>,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = NavigationBarDefaults.containerColor.copy(alpha = .7f),
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                alwaysShowLabel = false,
                icon = { Icon(painterResource(screen.iconRes), contentDescription = null) },
                label = { Text(stringResource(screen.labelRes)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route.pattern } == true,
                onClick = { navController.switchTo(screen.route.pattern) }
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