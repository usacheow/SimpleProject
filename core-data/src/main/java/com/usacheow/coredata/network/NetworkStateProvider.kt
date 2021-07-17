package com.usacheow.coredata.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkStateProvider @Inject constructor(
    @ApplicationCoroutineScope private val scope: CoroutineScope,
    connectivityManager:  ConnectivityManager,
) {

    private val _state = MutableStateFlow(true)
    val state = _state.asStateFlow()



    private val networkStateCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            scope.launch {
                _state.emit(true)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            scope.launch {
                _state.emit(false)
            }
        }
    }

    init {
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkStateCallback)
    }
}