package com.example.lt.customviewtest.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_motion.*

class MotionActivity:AppCompatActivity() {
    private var toggle = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion)
        ivIcon.setOnClickListener {
            if (toggle) {
                flContainer.addView(LayoutInflater.from(this)
                    .inflate(R.layout.inflate_custom, null))
                clMotion.transitionToEnd()
            }else{
                clMotion.transitionToStart()
            }
            toggle = !toggle
        }
    }
}