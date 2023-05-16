package com.usacheow.corenavigation

import androidx.core.net.toUri
import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Route : ScreenProvider

sealed class Deeplink(protected val value: String) {

    open val pattern: String = value

    open fun path(vararg args: Any): String = value
}

object AppRoute {

    object ExampleFirst : Route()
    data class ExampleSecond(val index: String) : Route()
}

object AppDeeplink {

    private val BaseUri = "app://com.usacheow.simpleapp".toUri()

    object Main : Deeplink("$BaseUri/main")

    object Details : Deeplink("$BaseUri/details") {

        const val IdKey = "id"

        override val pattern = "$value/{$IdKey}"
        override fun path(vararg args: Any) = "$value/%s".format(*args)
    }
}