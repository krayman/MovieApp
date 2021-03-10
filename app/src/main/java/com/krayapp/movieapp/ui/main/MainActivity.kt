package com.krayapp.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krayapp.movieapp.R
import com.krayapp.movieapp.ui.main.mainScreen.ListerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ListerFragment.newInstance())
                    .commitAllowingStateLoss()
        }
    }
}