package com.usacheow.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

abstract class NavigatorWrapper(protected val navigator: Navigator) {

    fun back() = navigator.pop()
}

@Composable
fun <T : NavigatorWrapper> rememberNavigator(block: (Navigator) -> T): T {
    val navigator = LocalNavigator.currentOrThrow
    return remember(navigator) { block(navigator) }
}