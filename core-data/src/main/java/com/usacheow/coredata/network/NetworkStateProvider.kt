package com.usacheow.coredata.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkStateProvider {

    val state: StateFlow<Boolean>
}

class NetworkStateProviderImpl @Inject constructor(
    connectivityManager: ConnectivityManager,
) : NetworkStateProvider {

    private val _state = MutableStateFlow(true)
    override val state = _state.asStateFlow()

    private val networkStateCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _state.tryEmit(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _state.tryEmit(false)
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .build()
        connectivityManager.registerNetworkCallback(request, networkStateCallback)
    }
}