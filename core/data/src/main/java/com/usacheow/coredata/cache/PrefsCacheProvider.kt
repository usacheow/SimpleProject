package com.usacheow.coredata.cache

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.usacheow.coredata.database.get
import com.usacheow.coredata.database.set
import com.usacheow.coredata.json.KotlinxSerializationJsonProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Collections
import javax.inject.Inject
import kotlin.reflect.KType
import kotlin.time.Duration
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.serializerOrNull

private const val CACHE_STORE = "cache_data"

class PrefsCacheProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonProvider: KotlinxSerializationJsonProvider,
) : CacheProvider {

    private val Context.dataStore by preferencesDataStore(name = CACHE_STORE)
    private val keysMap = Collections.synchronizedMap(
        mutableMapOf<KType, Preferences.Key<String>>()
    )

    override suspend fun <T> get(type: KType, key: String): T? {
        val dataSerializer = serializerOrNull(type) ?: return null

        return context.dataStore
            .get(getKey(type, key))
            .firstOrNull()
            ?.let { cacheItemJson -> jsonProvider.get().decodeFromString(cacheItemJson) as CacheElement<String> }
            ?.getOrClear { clear(type, key) }
            ?.data
            ?.let { dataJson -> jsonProvider.get().decodeFromString(dataSerializer, dataJson) as? T? }
    }

    override suspend fun <T : Any> save(type: KType, key: String, data: T, lifeDuration: Duration) {
        val dataSerializer = serializerOrNull(type) ?: return
        val dataJson = jsonProvider.get().encodeToString(dataSerializer, data)
        val cacheItem = CacheElement(dataJson, lifeDuration)
        val cacheItemJson = jsonProvider.get().encodeToString(cacheItem)
        context.dataStore.set(stringPreferencesKey(key), cacheItemJson)
    }

    override suspend fun clear(type: KType, key: String) {
        context.dataStore.edit { it.remove(getKey(type, key)) }
    }

    override suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }

    private fun getKey(type: KType, key: String) = keysMap.getOrPut(type) {
        stringPreferencesKey(key)
    }
}