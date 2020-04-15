package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.max

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getAvatar(300f.px.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        rotateX(30f)
        setLocation(0f,0f,-4f*resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.translate(width/2f,height/2f)
        canvas.rotate(-30f)
        canvas.clipRect(
            - bitmap.width.toFloat(),
             - bitmap.height.toFloat() ,
            bitmap.width.toFloat(),
            0f
        )
        canvas.rotate(30f)
        canvas.translate(-width/2f,-height/2f)
        canvas.drawBitmap(
            bitmap,
            width / 2f - bitmap.width / 2f,
            height / 2f - bitmap.height / 2f,
            paint
        )
        canvas.restore()

        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(
             - bitmap.width.toFloat(),
            0f,
            bitmap.width.toFloat(),
             bitmap.height.toFloat()
        )
        canvas.rotate(30f)
        canvas.translate(-width/2f,- height / 2f)
        canvas.drawBitmap(
            bitmap,
            width / 2f - bitmap.width / 2f,
            height / 2f - bitmap.height / 2f,
            paint
        )
        canvas.restore()
    }

    private fun getAvatar(width:Int):Bitmap{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.timg, options)
        options.inJustDecodeBounds = false
        options.inDensity = max(options.outWidth,options.outHeight)
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.timg,options)
    }
}