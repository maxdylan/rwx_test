package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.lt.customviewtest.extension.px

private val RADIUS = 50f.px
private val PADDING = 50f.px
class FixCircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize((PADDING * 2 + RADIUS * 2).toInt(), widthMeasureSpec)
        val height = resolveSize((PADDING * 2 + RADIUS * 2).toInt(), heightMeasureSpec)
        setMeasuredDimension(width,height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)

    }
}