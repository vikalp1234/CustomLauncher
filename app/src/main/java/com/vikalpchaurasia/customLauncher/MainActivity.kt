package com.vikalpchaurasia.customLauncher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeScreenFragment())
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
       // super.onBackPressed()
    }
}