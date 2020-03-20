package com.usacheow.coredata.cache

class CacheItem<T>(
    val data: T
) {
    val creationDate = System.currentTimeMillis()
}