package com.usacheow.coredata.network

import com.usacheow.coredata.storage.cacheprovider.CacheProvider
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

sealed class Tag

data class CachedRequestTag(
    val key: String,
    val cacheProvider: CacheProvider,
    val needActual: Boolean,
    val lifeDuration: Duration = 5.minutes,
) : Tag()

object AuthRequestTag : Tag()