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
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlin.math.sqrt


@Composable
fun ShakeDetector(
    shakeThreshold: Float = 12f, // You can adjust this threshold based on sensitivity needs
    onShake: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    // Time of last shake to avoid continuous activation
    var lastShakeTime by remember { mutableStateOf(0L) }


    //Use DisposableEffect to manage lifecycles correctly
    DisposableEffect(lifecycleOwner) {
        var acceleration = 10f
        var currentAcceleration = SensorManager.GRAVITY_EARTH
        var lastAcceleration = SensorManager.GRAVITY_EARTH

        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null) return
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                lastAcceleration = currentAcceleration
                currentAcceleration = sqrt(x * x + y * y + z * z).toDouble().toFloat()
                val delta = currentAcceleration - lastAcceleration
                acceleration = acceleration * 0.9F + delta

                if (acceleration > shakeThreshold) {
                    val currentTime = System.currentTimeMillis()
                    // Only trigger if 500ms has passed since the last trigger
                    if (currentTime - lastShakeTime > 500) {
                        lastShakeTime = currentTime
                        onShake()
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }

        val observer = LifecycleEventObserver { _, event ->
            if (event == Event.ON_RESUME) {
                sensorManager.registerListener(
                    sensorListener,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            } else if (event == Event.ON_PAUSE) {
                sensorManager.unregisterListener(sensorListener)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            sensorManager.unregisterListener(sensorListener)
        }
    }
}