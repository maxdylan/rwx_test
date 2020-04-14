package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.min

class TextImgView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getAvator(100f.px.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text =
        "Proin egestas nec purus vel pulvinar. Mauris rhoncus elit eu dolor malesuada, quis auctor augue congue. In at augue in elit sollicitudin eleifend sed volutpat tellus. Nam vitae bibendum urna. Aliquam viverra consectetur dignissim. Ut condimentum quis sapien in posuere. Morbi nec purus enim. Praesent dapibus neque et ipsum ultrices varius sit amet ac mi. Curabitur vestibulum neque non velit tincidunt dictum. Pellentesque luctus tincidunt nulla, at iaculis nisl. Morbi luctus massa at nulla aliquam fermentum. Donec facilisis congue mi vel interdum. Donec lectus erat, consequat sit amet mauris vel, bibendum placerat erat."


    private val fontMetrics = Paint.FontMetrics()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画图片
        canvas.drawBitmap(bitmap, width-100f.px, 100f.px, paint)
        // 画文字
        val measureArray = floatArrayOf(0f)
        paint.textSize = 24f.px
        paint.getFontMetrics(fontMetrics)
        var start = 0
        var count:Int
        var verticalOffset = -fontMetrics.top
        var maxWidth:Int
        while (start < text.length) {
            maxWidth =
                if (verticalOffset + fontMetrics.bottom < 100f.px || verticalOffset + fontMetrics.top > 100f.px + bitmap.height) {
                    width
                }else{
                    width-100f.px.toInt()
                }
            count = paint.breakText(text, start, text.length, true, maxWidth.toFloat(), measureArray)
            canvas.drawText(text,start,start + count,0f,verticalOffset,paint)
            start+=count
            verticalOffset += paint.fontSpacing
        }


    }

    private fun getAvator(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.timg, options)
        options.inJustDecodeBounds = false
        options.inDensity = min(
            options.outWidth, options.outHeight
        )
        options . inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.timg,options)

    }
}