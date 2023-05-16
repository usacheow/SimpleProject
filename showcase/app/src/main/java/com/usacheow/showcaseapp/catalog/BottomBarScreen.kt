package com.usacheow.showcaseapp.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.usacheow.coreuicompose.tools.LocalBottomNavigationHeight
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBarConfig
import com.usacheow.showcaseapp.DemoScreens

class BottomBarScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(FirstTab) {
            Box(modifier = Modifier.fillMaxSize()) {
                CompositionLocalProvider(LocalBottomNavigationHeight provides 80.dp) {
                    CurrentScreen()
                    NavigationBar(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        containerColor = NavigationBarDefaults.containerColor.copy(alpha = SimpleTopAppBarConfig.ContainerColorAlpha),
                    ) {
                        TabNavigationItem(FirstTab)
                        TabNavigationItem(SecondTab)
                        TabNavigationItem(ThirdTab)
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                tab.options.icon?.let { icon ->
                    Icon(painter = icon, contentDescription = tab.options.title)
                }
            }
        )
    }
}

object FirstTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "first"
            val icon = rememberVectorPainter(Icons.Filled.AccountCircle)

            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {
        val screen = rememberScreen(provider = DemoScreens.Palette)
        Navigator(screen)
    }
}

object SecondTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "second"
            val icon = rememberVectorPainter(Icons.Filled.AccountCircle)

            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {
        val screen = rememberScreen(provider = DemoScreens.Inputs)
        Navigator(screen)
    }
}

object ThirdTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "third"
            val icon = rememberVectorPainter(Icons.Filled.AccountCircle)

            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {
        val screen = rememberScreen(provider = DemoScreens.Demo)
        Navigator(screen)
    }
}