package com.usacheow.corenavigation

import androidx.core.net.toUri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable

const val DefaultArgKey = "arg"

sealed class Route(protected val value: String) {

    open val pattern: String = value
    open val arguments: List<NamedNavArgument> = emptyList()

    open fun path(vararg args: Any): String = value
}

sealed class RouteWithArg(value: String) : Route(value) {

    override val pattern = "$value?$DefaultArgKey={$DefaultArgKey}"
    override val arguments = listOf(navArgument(DefaultArgKey) { type = NavType.StringType })

    override fun path(vararg args: Any): String = "$value?$DefaultArgKey=${args.first()}"
}

sealed class Deeplink(protected val value: String) {

    open val pattern: String = value

    open fun path(vararg args: Any): String = value
}

object AppRoute {

    object ExampleFirst : Route("ExampleFirst")
    object ExampleSecond : RouteWithArg("ExampleSecond") {
        @Serializable
        data class Args(val index: String)
    }
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