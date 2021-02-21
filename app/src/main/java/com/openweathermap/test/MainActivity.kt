package com.openweathermap.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.openweathermap.test.view.WeatherFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.weather_activity_fragment_container, WeatherFragment::class.java, null)
            .commit()
    }
}