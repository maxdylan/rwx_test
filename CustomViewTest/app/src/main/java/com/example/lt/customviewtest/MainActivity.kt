package com.example.lt.customviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDashboard.setOnClickListener {
            // todo 跳转到仪表盘
        }
        btnPie.setOnClickListener {
            // todo 跳转到饼图
        }
    }
}
