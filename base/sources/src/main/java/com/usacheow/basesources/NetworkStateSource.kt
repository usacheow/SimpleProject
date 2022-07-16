package com.usacheow.basesources

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn

interface NetworkStateSource {

    val state: StateFlow<State>

    enum class State {
        Available, Unavailable,
    }
}

@SuppressLint("MissingPermission")
class NetworkStateSourceImpl @Inject constructor(
    connectivityManager: ConnectivityManager,
    @ApplicationCoroutineScope private val scope: CoroutineScope,
) : NetworkStateSource {

    override val state = connectivityManager
        .networkChangesFlow()
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 1_000, replayExpirationMillis = 1_000),
            initialValue = networkState(connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true),
        )

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    private fun ConnectivityManager.networkChangesFlow() = callbackFlow {
        val listener = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(NetworkStateSource.State.Available)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(NetworkStateSource.State.Unavailable)
            }
        }

        registerNetworkCallback(networkRequest, listener)
        awaitClose { unregisterNetworkCallback(listener) }
    }

    private fun networkState(isAvailable: Boolean) = when {
        isAvailable -> NetworkStateSource.State.Available
        else -> NetworkStateSource.State.Unavailable
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkStateSourceModule {

    @Binds
    @Singleton
    fun networkStateSource(source: NetworkStateSourceImpl): NetworkStateSource
}