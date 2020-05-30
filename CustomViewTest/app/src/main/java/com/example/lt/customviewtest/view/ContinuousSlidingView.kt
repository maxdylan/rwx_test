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

class ContinuousSlidingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

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
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                tracePointId = event.getPointerId(event.actionIndex)
                downX = event.getX(event.actionIndex)
                downY = event.getY(event.actionIndex)

                originX = offsetX
                originY = offsetY
            }

            MotionEvent.ACTION_POINTER_UP -> {
                val currentId = event.getPointerId(event.actionIndex)
                val traceIndex = event.findPointerIndex(tracePointId)

                if (currentId == tracePointId) {
                    // 只有在抬起手指是活动手指时候，才有关心的必要

                    val newIndex = if (traceIndex == event.pointerCount - 1) {
                        event.pointerCount -2
                    }else{
                        event.pointerCount -1
                    }

                    tracePointId = event.findPointerIndex(newIndex)

                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)

                    originX = offsetX
                    originY = offsetY
                }

            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = event.getX(event.findPointerIndex(tracePointId)) - downX + originX
                offsetY = event.getY(event.findPointerIndex(tracePointId)) - downY + originY
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