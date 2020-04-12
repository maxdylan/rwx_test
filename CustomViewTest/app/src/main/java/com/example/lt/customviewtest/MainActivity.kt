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
            // 跳转到仪表盘
            startActivity(Intent(this,DashboardActivity::class.java))
        }
        btnPie.setOnClickListener {
            // 跳转到饼图
            startActivity(Intent(this,PieActivity::class.java))
        }
        btnXfer.setOnClickListener {
            // 跳转到圆形头像
            startActivity(Intent(this,AvatorActivity::class.java))
        }
    }
}
