package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.min

class AvatorView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bound = RectF(50f, 50f, 250f.px, 250f.px)
    private val rectF = RectF(50f.px,50f.px,250f.px,250f.px)
    private val borderRectF = RectF(46f.px,46f.px,254f.px,254f.px)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        bitmap = getAvator(200f.px.toInt())
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画相框
        paint.color = Color.RED
        canvas.drawOval(borderRectF,paint)
        // 画头像
        val count = canvas.saveLayer(bound,null)
        canvas.drawOval(rectF, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, 50f.px, 50f.px, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)



    }

    private fun getAvator(width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,R.drawable.timg,option)
        option.inJustDecodeBounds = false
        option.inDensity = min(option.outWidth, option.outHeight)
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.timg,option)
    }
}