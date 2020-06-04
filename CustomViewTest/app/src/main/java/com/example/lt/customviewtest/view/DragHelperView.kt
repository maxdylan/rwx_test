package com.example.lt.customviewtest.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper


private const val COL_NUM = 2
private const val ROW_NUM = 3

class DragHelperView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(MeasureSpec.makeMeasureSpec(width / COL_NUM, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height / ROW_NUM, MeasureSpec.EXACTLY))
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        var childRight: Int
        var childBottom: Int
        children.forEachIndexed { index, view ->
            childLeft = (width / COL_NUM) * (index % 2)
            childTop = (height / ROW_NUM) * (index / 2)
            childRight = childLeft + width / COL_NUM
            childBottom = childTop + height / ROW_NUM

            view.layout(childLeft, childTop, childRight, childBottom)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    inner class MyDragHelperCallback : ViewDragHelper.Callback() {
        var captureLeft: Int = 0
        var captureTop: Int = 0
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                capturedChild.elevation = capturedChild.elevation + 1
            }
            captureLeft = capturedChild.left
            captureTop = capturedChild.top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            dragHelper.settleCapturedViewAt(captureLeft, captureTop)
            postInvalidateOnAnimation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }


    private val dragHelper = ViewDragHelper.create(this, MyDragHelperCallback())
}