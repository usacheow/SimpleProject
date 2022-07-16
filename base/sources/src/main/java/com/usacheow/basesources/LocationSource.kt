package com.usacheow.basesources

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.usacheow.corecommon.model.AppError
import com.usacheow.corecommon.model.Effect
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val MIN_TIME_IN_MILLISECONDS = 400L
private const val MIN_DISTANCE_IN_METRES = 1f

interface LocationSource {

    val state: Flow<Effect<State>>

    fun getLastKnownLocation(): Effect<State>

    data class State(
        val latitude: Double,
        val longitude: Double,
    )
}

@SuppressLint("MissingPermission")
class LocationSourceImpl @Inject constructor(
    private val locationManager: LocationManager,
) : LocationSource {

    override val state = locationManager.currentLocationFlow()

    override fun getLastKnownLocation(): Effect<LocationSource.State> {
        return try {
            locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                ?.toSimpleLocation()
                ?.let { Effect.success(it) }
                ?: Effect.error(AppError.Unknown())
        } catch (e: Exception) {
            Effect.error(AppError.Unknown(cause = e))
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
            val exception = AppError.Unknown(cause = e)
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

fun Location.toSimpleLocation() = LocationSource.State(
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

@Module
@InstallIn(SingletonComponent::class)
interface LocationSourceModule {

    @Binds
    @Singleton
    fun locationSource(source: LocationSourceImpl): LocationSource
}