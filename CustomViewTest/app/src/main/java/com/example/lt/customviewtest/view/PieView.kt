package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.lt.customviewtest.extension.px
import kotlin.math.cos
import kotlin.math.sin

class PieView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val angles = mutableListOf(30f, 60f, 90f, 120f, 60f)
    private val colors =
        mutableListOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var rectF: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF =
            RectF(
                width / 2f - 150f.px,
                height / 2f - 150f.px,
                width / 2f + 150f.px,
                height / 2f + 150f.px
            )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        angles.forEachIndexed { index, angle ->
            paint.color = colors[index]

            if (index == 3) {
                canvas.save()
                val radians = Math.toRadians((startAngle + angle / 2f).toDouble()).toFloat()
                canvas.translate(
                    20f.px * cos(radians),
                    20f.px * sin(radians)
                )
            }

            canvas.drawArc(rectF, startAngle, angle, true, paint)
            startAngle += angle
            if (index == 3) {
                canvas.restore()
            }
        }
    }
}