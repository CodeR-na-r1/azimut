package com.mrx.compas

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sManager: SensorManager
    var degreesDirection: Float = 0f

    lateinit var viewDegrees: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewDegrees = findViewById(R.id.viewDegreesCount)

        sManager = getSystemService(SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()

        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()

        sManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null){
            degreesDirection = event.values[0]
            this.viewDegrees.text = degreesDirection.toString()
            Log.d("myLog", degreesDirection.toString())
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//        No implement
    }
}