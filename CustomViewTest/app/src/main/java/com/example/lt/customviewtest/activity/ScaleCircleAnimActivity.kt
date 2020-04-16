package com.example.lt.customviewtest.activity

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_scale_circle_anim.*

class ScaleCircleAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_circle_anim)
        val animator = ObjectAnimator.ofFloat(circleView, "radius", 300f)
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
    }
}
