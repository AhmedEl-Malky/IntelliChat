package com.elmalky.chathub.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.chathub.R
import com.elmalky.chathub.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binder:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}