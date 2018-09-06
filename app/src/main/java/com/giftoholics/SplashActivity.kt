package com.giftoholics

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
                {
                    val intent= Intent(this,SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }, DELAY_DURATION
        )
    }
    companion object {
        const val DELAY_DURATION:Long=4000
    }
}
