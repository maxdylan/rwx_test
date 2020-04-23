package com.example.lt.customviewtest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_custom_layout.*

class CustomLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_layout)
        btnTabLayout.setOnClickListener {
            startActivity(Intent(this, TabLayoutActivity::class.java))
        }

        btnSquareImg.setOnClickListener {
            startActivity(Intent(this, SquareImgActivity::class.java))
        }

        btnCircleView.setOnClickListener {
            startActivity(Intent(this, FixCircleActivity::class.java))
        }
    }
}
