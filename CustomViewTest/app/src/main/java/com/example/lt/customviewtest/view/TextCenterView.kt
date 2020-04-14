package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.lt.customviewtest.extension.px

class TextCenterView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var circleRectF:RectF
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textBounds = Rect()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleRectF = RectF(
            width / 2f - 150f.px,
            height / 2f - 150f.px,
            width / 2f + 150f.px,
            height / 2f + 150f.px
        )
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画圆
        paint.color = Color.GRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f.px
        canvas.drawOval(circleRectF, paint)
        // 画弧
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(circleRectF, 140f, 200f, false, paint)
        // 画字
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 80f.px
        paint.color = Color.BLACK
        paint.getTextBounds("abap",0,"abap".length,textBounds)
        // 这里的y为什么要用减法。。。
        canvas.drawText(
            "abap",
            width / 2f,
            height / 2f - (textBounds.top + textBounds.bottom) / 2,
            paint
        )


    }
}