package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

val menu = arrayOf("蒸羊羔","蒸熊掌","蒸鹿尾儿","烧花鸭","烧雏鸡","烧子鹅","卤猪","卤鸭","酱鸡","腊肉","松花小肚儿","晾肉","香肠儿","什锦苏盘","熏鸡白肚儿","清蒸八宝猪","江米酿鸭子")
class SimpleTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var dish = menu[0]
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 60f

        canvas.drawText("我请您吃:", width / 2f, height / 2f - 40, paint)
        paint.textSize = 100f
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(dish, width / 2f, height / 2f - fontMetrics.top + 10, paint)

    }
}