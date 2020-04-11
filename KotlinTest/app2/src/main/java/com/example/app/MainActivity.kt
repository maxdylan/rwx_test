package com.example.app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity

class MainActivity :AppCompatActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"

    private lateinit var etUserName:EditText
    private lateinit var etPassword:EditText
    private lateinit var etCode:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUserName = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)

        etUserName.setText(CacheUtils.get(usernameKey))
        etPassword.setText(CacheUtils.get(passwordKey))

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val imgCode: CodeView = findViewById(R.id.code_view)
        btnLogin.setOnClickListener(this)
        imgCode.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            is CodeView -> view.updateCode()
            is Button -> login()
        }
    }

    private fun login(){
        val username = etUserName.text.toString()
        val password = etPassword.text.toString()
        val code = etCode.text.toString()

        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }

    }

    private fun verify(user: User): Boolean {
        return if (user.username != null && user.username.length < 4) {
            Utils.toast("用户名不合法")
            false
        }else if (user.password != null && user.password.length < 4) {
            Utils.toast("密码不合法")
            false
        } else {
            true
        }
    }
}