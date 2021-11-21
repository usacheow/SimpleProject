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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.util.Collections
import javax.inject.Inject

private const val CACHE_STORE = "cache_data"

class PrefsCacheProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonProvider: KotlinxSerializationJsonProvider,
) : CacheProvider {

    private val Context.dataStore by preferencesDataStore(name = CACHE_STORE)
    private val keysMap = Collections.synchronizedMap(
        mutableMapOf<Class<*>, Preferences.Key<String>>()
    )


    override suspend fun <T> get(clazz: Class<T>, key: String): T? {
        return context.dataStore
            .get(getKey(clazz, key))
            .firstOrNull()
            ?.let { json -> jsonProvider.get().decodeFromString(json) as CacheElement<T> }
            ?.getOrClear { clear(clazz, key) }
            ?.data
    }

    override suspend fun <T : Any> save(key: String, data: T, lifeTimeInMillis: Long) {
        val cache = CacheElement(data, lifeTimeInMillis)
        val json = jsonProvider.get().encodeToString(cache)
        context.dataStore.set(stringPreferencesKey(key), json)

    }

    override suspend fun <T> clear(clazz: Class<T>, key: String) {
        context.dataStore.edit { it.remove(getKey(clazz, key)) }
    }

    override suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }

    private fun <T> getKey(clazz: Class<T>, key: String) = keysMap.getOrPut(clazz) {
        stringPreferencesKey(key)
    }
}