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
    private var originY = 0f
    private var currentY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN->{
                currentPointerIndex = event.findPointerIndex(event.actionIndex)
                originY = event.getY(currentPointerIndex)
            }

            MotionEvent.ACTION_POINTER_DOWN->{
                currentPointerIndex = event.findPointerIndex(event.actionIndex)
                originY = event.getY(currentPointerIndex)
            }

            MotionEvent.ACTION_MOVE->{
                currentY = event.getY(currentPointerIndex)
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        translationY += currentY - originY
        canvas.drawBitmap(bitmap, width / 2 - 100f.px, translationY, paint)


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