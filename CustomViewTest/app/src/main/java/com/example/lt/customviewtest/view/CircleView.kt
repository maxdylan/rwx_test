package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var radius = 100f
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)

    }
}