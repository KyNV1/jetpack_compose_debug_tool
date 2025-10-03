package com.example.jetpack_compose_debug_tool.debug

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlin.math.sqrt


@Composable
fun ShakeDetector(onShake: () -> Unit) {
    val context = LocalContext.current
    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    DisposableEffect(Unit) {
        var acceleration = 10f
        var currentAcceleration = SensorManager.GRAVITY_EARTH
        var lastAcceleration = SensorManager.GRAVITY_EARTH

        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if(event==null) return
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                lastAcceleration = currentAcceleration
                currentAcceleration = sqrt(x * x + y * y + z * z).toDouble().toFloat()
                val delta = currentAcceleration - lastAcceleration
                acceleration = acceleration * 0.9F + delta
                if(acceleration>12){
                    onShake()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

        }

        sensorManager.registerListener(
            sensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}