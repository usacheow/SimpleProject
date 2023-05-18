package com.usacheow.coredata

import android.content.Context
import com.usacheow.coredata.network.LocalDispatchers
import com.usacheow.coredata.network.json.KotlinxSerializationJsonProvider
import com.usacheow.coredata.network.Network
import com.usacheow.coredata.network.NetworkImpl
import com.usacheow.coredata.storage.cacheprovider.CacheCleaner
import com.usacheow.coredata.storage.cacheprovider.CacheProvider
import com.usacheow.coredata.storage.cacheprovider.LruCacheProvider
import com.usacheow.coredata.storage.database.AppDatabase
import com.usacheow.coredata.storage.preferences.PreferencesProvider
import com.usacheow.coredata.storage.preferences.TokenStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val coreDataDiModule by DI.Module {
    importOnce(coreDataStorageDiModule)
    importOnce(coreDataNetworkDiModule)
}

val coreDataStorageDiModule by DI.Module {
    bindFactory { context: Context -> AppDatabase.newInstance(context) }
    bindFactory { context: Context -> PreferencesProvider(context) }
    bindSingleton { TokenStorage(instance()) }
    bindSingleton<CacheProvider> { LruCacheProvider() }
    bindFactory<CacheProvider, CacheCleaner> { it }
}

val coreDataNetworkDiModule by DI.Module {
    bindSingleton { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    bindSingleton(tag = LocalDispatchers.IO) { Dispatchers.IO }
    bindSingleton(tag = LocalDispatchers.Default) { Dispatchers.Default }
    bindSingleton(tag = LocalDispatchers.Main) { Dispatchers.Main }
    bindSingleton { KotlinxSerializationJsonProvider() }
    bindSingleton<Network> { NetworkImpl(instance(), instance(), instance()) }
}