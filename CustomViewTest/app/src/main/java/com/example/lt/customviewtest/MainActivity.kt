package com.example.lt.customviewtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lt.customviewtest.view.CameraView
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
        btnTextCenter.setOnClickListener {
            // 跳转到文字居中
            startActivity(Intent(this, TextCenterActivity::class.java))
        }
        btnTextImg.setOnClickListener {
            // 跳转到图文混排
            startActivity(Intent(this, TextImgActivity::class.java))
        }
        btnCamera.setOnClickListener {
            // 跳转到范围裁切和几何变换
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}
