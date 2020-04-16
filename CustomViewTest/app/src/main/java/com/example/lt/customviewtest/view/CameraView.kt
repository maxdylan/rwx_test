package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.example.lt.customviewtest.R
import com.example.lt.customviewtest.extension.px
import kotlin.math.max

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getAvatar(300f.px.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        setLocation(0f,0f,-6f*resources.displayMetrics.density)
    }

    var leftCameraAngle = 0f
        set(value) {
            field = value
            invalidate()
        }
    var rightCameraAngle = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.translate(width/2f,height/2f)
        canvas.rotate(-flipAngle)
        camera.save()
        camera.rotateX(leftCameraAngle)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(
            - bitmap.width.toFloat(),
             - bitmap.height.toFloat() ,
            bitmap.width.toFloat(),
            0f
        )
        canvas.rotate(flipAngle)
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
        canvas.rotate(-flipAngle)
        camera.save()
        camera.rotateX(rightCameraAngle)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(
             - bitmap.width.toFloat(),
            0f,
            bitmap.width.toFloat(),
             bitmap.height.toFloat()
        )
        canvas.rotate(flipAngle)
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
        BitmapFactory.decodeResource(resources, R.drawable.squire, options)
        options.inJustDecodeBounds = false
        options.inDensity = max(options.outWidth,options.outHeight)
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.squire,options)
    }
}