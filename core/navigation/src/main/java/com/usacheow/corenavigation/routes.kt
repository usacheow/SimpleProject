package com.usacheow.corenavigation

import androidx.core.net.toUri
import kotlinx.serialization.Serializable

sealed class Route(val path: String)

sealed class RouteWithArg(val path: String)

object AppRoute {

    object MainBottomBar : Route("MainBottomBar") {

        object MainTab1 : Route("mainTab1")
        object MainTab2 : Route("mainTab2")
        object MainTab3 : Route("mainTab3")

        object Mock1 : Route("Mock1")
        object Mock1Second : Route("Mock1Second")
        object Mock2 : Route("Mock2")
        object Mock2Second : Route("Mock2Second")
        object Mock3 : Route("Mock3")
        object Mock3Second : Route("Mock3Second")
    }

    object ExampleFirst : Route("ExampleFirst")
    object ExampleSecond : RouteWithArg("ExampleSecond") {
        @Serializable
        data class Args(val index: String)
    }
}

object AppDeeplink {

    private val BaseUri = "app://com.usacheow.simpleapp".toUri()

    object Main : Route("$BaseUri/main")
}