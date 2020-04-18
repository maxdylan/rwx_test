package com.example.lt.customviewtest.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val floatHintSize = 14f.px
    private val floatOffset = 4f.px
    private val hintPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = floatHintSize
        alpha = 0
    }

    private var isFloatShown = false
    private var useFloatHint = false

    init {
        setPadding(paddingLeft, (paddingTop + floatHintSize).toInt(), paddingRight, paddingBottom)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatHint = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatHint, true)
        typedArray.recycle()
    }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "hintAlpha", 1f)
    }


    var hintAlpha = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (!text.isNullOrEmpty()&&!isFloatShown) {
            // 从无到有
            isFloatShown = true
            animator.start()
        }else if(text.isNullOrEmpty()&&isFloatShown) {
            isFloatShown = false
            animator.reverse()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(useFloatHint) {
            hintPaint.alpha = (0xff * hintAlpha).toInt()
            canvas.drawText(
                hint.toString(),
                floatOffset,
                floatHintSize + 10f.px * (1 - hintAlpha),
                hintPaint
            )
        }

    }

}