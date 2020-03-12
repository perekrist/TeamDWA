package com.mvp_kotlin_login_register_retrofit_example.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mvp_kotlin_login_register_retrofit_example.TakePhotoActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()
        handler.postDelayed({ finish() }, 1000)

        val intent = Intent(this, TakePhotoActivity::class.java)
        startActivity(intent)
        finish()
    }
}
