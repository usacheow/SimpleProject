package com.usacheow.coredata.location

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.usacheow.core.Effect
import com.usacheow.coredata.network.ApiError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

private const val MIN_TIME_IN_MILLISECONDS = 400L
private const val MIN_DISTANCE_IN_METRES = 1f

interface LocationProvider {

    val state: Flow<Effect<SimpleLocation>>

    fun getLastKnownLocation(): Effect<SimpleLocation>
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalCoroutinesApi::class)
class LocationProviderImpl @Inject constructor(
    private val locationManager: LocationManager,
) : LocationProvider {

    override val state = locationManager.currentLocationFlow()

    override fun getLastKnownLocation(): Effect<SimpleLocation> {
        return try {
            locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                ?.toSimpleLocation()
                ?.let { Effect.success(it) }
                ?: Effect.error(ApiError.EmptyResponseException())
        } catch (e: Exception) {
            Effect.error(ApiError.SecurityException())
        }
    }

    private fun LocationManager.currentLocationFlow() = callbackFlow {
        val listener = EmptyLocationListener { location ->
            location?.toSimpleLocation()
                ?.let { Effect.success(it) }
                ?.let(::trySend)
        }
        try {
            registerListener(listener)
        } catch (e: Exception) {
            val exception = when (e) {
                is SecurityException ->  ApiError.SecurityException()
                else -> ApiError.UnknownException()
            }
            send(Effect.error(exception))
        }
        awaitClose { unregisterListener(listener) }
    }

    private fun LocationManager.registerListener(listener: LocationListener) = requestLocationUpdates(
        LocationManager.NETWORK_PROVIDER,
        MIN_TIME_IN_MILLISECONDS,
        MIN_DISTANCE_IN_METRES,
        listener,
    )

    private fun LocationManager.unregisterListener(listener: LocationListener) = removeUpdates(listener)
}

data class SimpleLocation(
    val latitude: Double,
    val longitude: Double,
)

fun Location.toSimpleLocation() = SimpleLocation(
    latitude = latitude,
    longitude = longitude,
)

class EmptyLocationListener(
    private val onStatusChangedAction: (String?, Int, Bundle?) -> Unit = { _, _, _ -> },
    private val onProviderEnabledAction: (String?) -> Unit = {},
    private val onProviderDisabledAction: (String?) -> Unit = {},
    private val onLocationChangedAction: (Location?) -> Unit,
) : LocationListener {

    override fun onLocationChanged(location: Location) {
        onLocationChangedAction(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        onStatusChangedAction(provider, status, extras)
    }

    override fun onProviderEnabled(provider: String) {
        onProviderEnabledAction(provider)
    }

    override fun onProviderDisabled(provider: String) {
        onProviderDisabledAction(provider)
    }
}