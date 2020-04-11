package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

val displayMetrics = Resources.getSystem().displayMetrics!!

fun dp2px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)

fun toast(message: String, length: Int = Toast.LENGTH_LONG) =
    Toast.makeText(BaseApplication.currentApplication, message, length).show()

