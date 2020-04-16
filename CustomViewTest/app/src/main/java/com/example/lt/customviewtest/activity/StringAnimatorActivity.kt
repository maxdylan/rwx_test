package com.example.lt.customviewtest.activity

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.view.menu
import kotlinx.android.synthetic.main.activity_string_animator.*

class StringAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_animator)

        val animator = ObjectAnimator.ofObject(simpleTextView, "dish", TextEvaluator(), "江米酿鸭子")
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.startDelay = 1000
        animator.duration = 5000
        animator.start()
    }

    class TextEvaluator:TypeEvaluator<String>{
        override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
            val length = (fraction*menu.size).toInt()
            return menu[if(length==menu.size) length-1 else length]
        }

    }
}
