package com.example.lt.aptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.lt.lib_reflection.BindUtil
import com.example.lt.lib_reflection.BindView

class MainActivity : AppCompatActivity() {
    @BindView(R.id.tvCenter)
    lateinit var tvCenter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BindUtil.bind(this)
        tvCenter.text = "你好啊，世界~"
    }
}