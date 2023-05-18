package com.usacheow.coreui

import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.Context
import android.hardware.SensorManager
import android.location.LocationManager
import android.net.ConnectivityManager
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindSingleton
import org.kodein.di.contexted
import org.kodein.di.diContext
import org.kodein.di.instance

val coreUiDiModule by DI.Module {
    bindFactory { context: Context ->
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    bindFactory { context: Context ->
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    bindFactory { context: Context ->
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    bindFactory { context: Context ->
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    bindFactory { context: Context ->
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}