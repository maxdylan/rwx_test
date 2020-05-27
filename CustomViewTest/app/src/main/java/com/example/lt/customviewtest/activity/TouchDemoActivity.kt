package com.example.lt.customviewtest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_touch_demo.*

class TouchDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_demo)
        btnScalableImageView.setOnClickListener{
            startActivity(Intent(this,ScalableImageViewActivity::class.java))
        }
        btnContinuousSlidingView.setOnClickListener {
            startActivity(Intent(this, ContinuousSlidingViewActivity::class.java))
        }
    }
}