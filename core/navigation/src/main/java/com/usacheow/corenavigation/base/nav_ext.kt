package com.usacheow.corenavigation.base

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

typealias ScreenGraphBuilder = ScreenRegistry.() -> Unit

fun featureGraph(block: ScreenGraphBuilder): ScreenGraphBuilder = { block() }

inline fun <reified T : ScreenProvider> ScreenRegistry.screen(
    noinline content: (T) -> Screen,
) = register(content)

inline fun <reified T : ScreenProvider> Navigator.push(
    provider: T,
) = push(ScreenRegistry.get(provider))

inline fun <reified T : ScreenProvider> Navigator.push(
    vararg provider: T,
) = push(provider.map { ScreenRegistry.get(it) })

inline fun <reified T : ScreenProvider> Navigator.replace(
    provider: T,
) = replace(ScreenRegistry.get(provider))

inline fun <reified T : ScreenProvider> Navigator.replaceAll(
    provider: T,
) = replaceAll(ScreenRegistry.get(provider))

inline fun <reified T : ScreenProvider> Navigator.replaceAll(
    vararg provider: T,
) = replaceAll(provider.map { ScreenRegistry.get(it) })