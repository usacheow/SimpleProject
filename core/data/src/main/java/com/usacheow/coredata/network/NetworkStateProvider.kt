package com.usacheow.coredata.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn

interface NetworkStateProvider {

    val state: StateFlow<NetworkState>
}

enum class NetworkState {
    Available, Unavailable,
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalCoroutinesApi::class)
class NetworkStateProviderImpl @Inject constructor(
    connectivityManager: ConnectivityManager,
    @ApplicationCoroutineScope private val scope: CoroutineScope,
) : NetworkStateProvider {

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
                trySend(NetworkState.Available)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(NetworkState.Unavailable)
            }
        }

        registerNetworkCallback(networkRequest, listener)
        awaitClose { unregisterNetworkCallback(listener) }
    }

    private fun networkState(isAvailable: Boolean) = when {
        isAvailable -> NetworkState.Available
        else -> NetworkState.Unavailable
    }
}