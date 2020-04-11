package com.example.lt.customviewtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDashboard.setOnClickListener {
            // todo 跳转到仪表盘
            startActivity(Intent(this,DashboardActivity::class.java))
        }
        btnPie.setOnClickListener {
            // todo 跳转到饼图
        }
    }
}
