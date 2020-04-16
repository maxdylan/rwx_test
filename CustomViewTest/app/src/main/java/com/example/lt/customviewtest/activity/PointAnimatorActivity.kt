package com.example.lt.customviewtest.activity

import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.graphics.PointF
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_point_animator.*

class PointAnimatorActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_animator)

        val animator =
            ObjectAnimator.ofObject(pointView, "point", PointFEvaluator(), PointF(500f, 500f))
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
    }
}
