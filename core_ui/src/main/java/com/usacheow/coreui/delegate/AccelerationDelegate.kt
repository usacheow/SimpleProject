package com.usacheow.coreui.delegate

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.usacheow.coreui.BuildConfig
import javax.inject.Inject
import kotlin.math.sqrt

private const val SHAKE_ACCELERATION = 15
private const val BUILD_TYPE_DEBUG = "debug"
private const val BUILD_TYPE_BETA = "beta"

class AccelerationDelegate
@Inject constructor() {

    private lateinit var sensorManager: SensorManager

    private var listener: OnShakeListener? = null

    private var acceleration = 10f
    private var currentAcceleration = SensorManager.GRAVITY_EARTH
    private var lastAcceleration = SensorManager.GRAVITY_EARTH

    private val sensorListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt(x * x + y * y + z * z)
            val delta = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > SHAKE_ACCELERATION) {
                sensorManager.unregisterListener(this)
                listener?.onShakeDetected()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit
    }

    fun onCreate(sensorManager: SensorManager) {
        this.sensorManager = sensorManager
    }

    fun onResume(listener: OnShakeListener) {
        this.listener = listener

        if (BuildConfig.BUILD_TYPE == BUILD_TYPE_DEBUG || BuildConfig.BUILD_TYPE == BUILD_TYPE_BETA) {
            sensorManager.registerListener(
                sensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun onPause() {
        listener = null
        sensorManager.unregisterListener(sensorListener)
    }

    interface OnShakeListener {

        fun onShakeDetected()
    }

}
