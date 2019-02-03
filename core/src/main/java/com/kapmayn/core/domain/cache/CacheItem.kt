package com.kapmayn.core.domain.cache

class CacheItem<T>(
    val data: T
) {
    val creationDate = System.currentTimeMillis()
}