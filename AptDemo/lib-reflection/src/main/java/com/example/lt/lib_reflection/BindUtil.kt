package com.example.lt.lib_reflection

import android.app.Activity

class BindUtil {
    companion object{
        fun bind(activity:Activity){
            val fields = activity.javaClass.fields
            fields.forEach {field ->
                val annotation = field.getAnnotation(BindView::class.java)
                if (annotation != null) {

                }
            }
        }
    }
}