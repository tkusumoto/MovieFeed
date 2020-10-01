package com.kussuue.moviefeed.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kussuue.moviefeed.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MainFragment()).commit()
    }
}
