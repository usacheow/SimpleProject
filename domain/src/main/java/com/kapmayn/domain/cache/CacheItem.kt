package com.kapmayn.domain.cache

class CacheItem<T>(
    val data: T
) {
    val creationDate = System.currentTimeMillis()
}