package com.example.lt.customviewtest.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_flip_animator.*

class FlipAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_animator)
        val step1 = ObjectAnimator.ofFloat(cameraView, "rightCameraAngle", 30f)
        step1.startDelay = 1000
        step1.duration = 1000
        val step2 = ObjectAnimator.ofFloat(cameraView, "flipAngle", 270f+180f)
        step2.duration = 2000
        val step3 = ObjectAnimator.ofFloat(cameraView, "leftCameraAngle", -30f)
        step3.duration = 1000
        val animator = AnimatorSet()
        animator.playSequentially(step1,step2,step3)
        animator.start()
    }
}
