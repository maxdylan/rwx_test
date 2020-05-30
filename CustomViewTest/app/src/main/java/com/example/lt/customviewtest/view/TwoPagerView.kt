package com.example.lt.customviewtest.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs
import kotlin.math.absoluteValue

class TwoPagerView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 先测量孩子
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        // 再测量自己
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        val childTop = 0
        var childRight = width
        val childBottom = height
        children.forEach {
            it.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f

    private val viewConfig = ViewConfiguration.get(context)
    private val overScroller = OverScroller(context)
    private val velocityTracker = VelocityTracker.obtain()


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()

            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.x - downX) > viewConfig.scaledTouchSlop) {
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN ->{
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                val scrollX = (downX - event.x + downScrollX).toInt().coerceAtLeast(0).coerceAtMost(width)
                scrollTo(scrollX, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000,
                    viewConfig.scaledMaximumFlingVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity

                val targetPage:Int = if(xVelocity.absoluteValue > viewConfig.scaledMinimumFlingVelocity){
                    if(xVelocity<0) 1 else 0
                }else{
                    if(scrollX>width/2) 1 else 0
                }
                val targetDistance = if(targetPage == 1) width - scrollX else -scrollX
                overScroller.startScroll(scrollX,0,targetDistance,0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, 0)
            postInvalidateOnAnimation()
        }
    }
}