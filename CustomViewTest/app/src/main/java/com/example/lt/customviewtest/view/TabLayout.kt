package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TabLayout(context: Context,attrs: AttributeSet) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 测量子view，然后测量自己
        var widthUsed = 0
        var heightUsed = 0
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var maxWidth = 0
        var maxHeight = 0
        children.forEachIndexed { index, child ->
            // 对子view进行一次一般测量
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            // 测量完成后开始考虑缓存测量数据，然后layout
            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBound = childrenBounds[index]
            if (widthUsed + child.measuredWidth > width) {
                // 需要折行
                widthUsed = 0
                if (maxHeight == 0) {
                    maxHeight = child.measuredHeight
                }
                heightUsed += child.measuredHeight
                maxHeight += heightUsed
            }
            childBound.set(
                widthUsed,
                heightUsed,
                widthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )
            widthUsed += child.measuredWidth
            maxWidth = max(maxWidth,widthUsed)
        }
        setMeasuredDimension(maxWidth,maxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, child ->
            val childBound = childrenBounds[index]
            child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?) = MarginLayoutParams(context, attrs)

}