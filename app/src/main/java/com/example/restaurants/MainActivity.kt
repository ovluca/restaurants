package com.example.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurants.ui.main.RestaurantsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RestaurantsFragment.newInstance())
                    .commitNow()
        }
    }
}