package com.example.lt.customviewtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

class DragListenerView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(MeasureSpec.makeMeasureSpec(width / 2, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height / 3, MeasureSpec.EXACTLY))
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        var childRight = 0
        var childBottom = 0
        children.forEachIndexed { index, view ->

            childLeft = (index % 2) * (width / 2)
            childRight = childLeft + width/2
            childTop = (index / 2) * (height / 3)
            childBottom = childTop + height/3

            view . layout (childLeft, childTop, childRight, childBottom)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        children.forEach {
            it.setOnLongClickListener {view->
                view.startDrag(null, DragShadowBuilder(view), view, 0)
                true
            }
        }
    }
}