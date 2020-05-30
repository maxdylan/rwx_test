package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.min

class CooperationSlidingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val bitmap = getAvatar(200f.px.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var currentPointerIndex = 0
    private var originX = 0f
    private var originY = 0f

    private var downX = 0f
    private var downY = 0f

    private var offsetX = 0f
    private var offsetY = 0f

    private var tracePointId = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val focusX: Float
        val focusY: Float

        var sumX = 0f
        var sumY = 0f

        for (i in 0 until event.pointerCount) {
            sumX += event.getX(i)
            sumY += event.getY(i)
        }

        val pointerCount = if (event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
            sumX -= event.getX(event.actionIndex)
            sumY -= event.getY(event.actionIndex)
            event.pointerCount - 1
        } else {
            event.pointerCount
        }

        focusX = sumX / pointerCount
        focusY = sumY / pointerCount

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                tracePointId = event.getPointerId(event.actionIndex)
                downX = focusX
                downY = focusY

                originX = offsetX
                originY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originX
                offsetY = focusY - downY + originY
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.squire, options)
        options.inJustDecodeBounds = false
        options.inDensity = min(
            options.outWidth, options.outHeight
        )
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.squire, options)
    }
}