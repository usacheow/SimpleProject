package com.usacheow.simpleapp

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.usacheow.corenavigation.AppRoute
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.LocalStringHolder
import org.kodein.di.compose.withDI

class App(private val windowSizeClass: WindowSizeClass) : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel<AppViewModel>()
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