package com.example.lt.customviewtest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lt.customviewtest.R
import kotlinx.android.synthetic.main.activity_animator.*

class AnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)
        btnScaleCircleAnimator.setOnClickListener {
            // 跳转到缩放圆动画
            startActivity(Intent(this, ScaleCircleAnimActivity::class.java))
        }
        btnFlipAnimator.setOnClickListener {
            // 跳转到翻折动画
            startActivity(Intent(this, FlipAnimatorActivity::class.java))
        }
        btnTextAnimator.setOnClickListener {
            // 跳转到文字动画
        }
        btnMovePointAnimator.setOnClickListener {
            // 跳转点移动动画
        }
    }
}
