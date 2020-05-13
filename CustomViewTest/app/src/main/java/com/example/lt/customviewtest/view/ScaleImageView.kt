package com.example.lt.customviewtest.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.max
import kotlin.math.min

private val BITMAP_WIDTH = 300f.px
private const val EXTRA_BIG_SCAL = 1.5F

class ScaleImageView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getAvatar(BITMAP_WIDTH.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var smallScale: Float = 0f
    private var bigScale: Float = 0f

    private var offsetX = 0f
    private var offsetY = 0f

    private val scaleAnimator =
        ObjectAnimator.ofFloat(this@ScaleImageView, "scale", smallScale, bigScale)

    private var isBig = false

    var scale: Float = smallScale
        set(value) {
            field = value
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        smallScale = width / BITMAP_WIDTH
        bigScale = height / BITMAP_WIDTH * EXTRA_BIG_SCAL

        scale = smallScale
        lastX = width / 2f
        lastY = height / 2f

        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleRate = (scale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleRate, offsetY * scaleRate)
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, (width - BITMAP_WIDTH) / 2f, (height - BITMAP_WIDTH) / 2f, paint)
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return scaleGestureDetector.isInProgress||gestureDetector.onTouchEvent(event)
    }

    private val gestureListener = LtGesture()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)

    private val scaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.OnScaleGestureListener {
            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {
            }

            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scale *= detector.scaleFactor
                return when {
                    scale > bigScale -> {
                        scale = bigScale
                        false
                    }
                    scale < smallScale -> {
                        scale = smallScale
                        false
                    }
                    else -> {
                        true
                    }
                }
            }
        })

    private var lastX = 0f
    private var lastY = 0f

    private val overScroller = OverScroller(context)
    private val flingRunnable = FlingRunnable()

    inner class LtGesture : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?,
                             e2: MotionEvent?,
                             velocityX: Float,
                             velocityY: Float): Boolean {
            if (isBig) {
                overScroller.fling(offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-(BITMAP_WIDTH * bigScale - width) / 2f).toInt(),
                    ((BITMAP_WIDTH * bigScale - width) / 2f).toInt(),
                    (-(BITMAP_WIDTH * bigScale - height) / 2f).toInt(),
                    ((BITMAP_WIDTH * bigScale - height) / 2f).toInt(),
                    40f.px.toInt(),
                    40f.px.toInt())
                ViewCompat.postOnAnimation(this@ScaleImageView, flingRunnable)

            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            if (isBig) {
                isBig = false
                scaleAnimator.reverse()
            } else {
                isBig = true
                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffset()
                scaleAnimator.start()
            }
            return super.onDoubleTap(e)
        }

        override fun onScroll(e1: MotionEvent?,
                              e2: MotionEvent?,
                              distanceX: Float,
                              distanceY: Float): Boolean {
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()
                invalidate()
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }

    inner class FlingRunnable : Runnable {
        override fun run() {
            if (overScroller.computeScrollOffset()) {
                offsetX = overScroller.currX.toFloat()
                offsetY = overScroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScaleImageView, flingRunnable)
            }
        }
    }

    private fun fixOffset() {
        offsetX = min(offsetX, (BITMAP_WIDTH * bigScale - width) / 2f)
        offsetX = max(offsetX, -(BITMAP_WIDTH * bigScale - width) / 2f)
        offsetY = min(offsetY, (BITMAP_WIDTH * bigScale - height) / 2f)
        offsetY = max(offsetY, -(BITMAP_WIDTH * bigScale - height) / 2f)
    }
}

