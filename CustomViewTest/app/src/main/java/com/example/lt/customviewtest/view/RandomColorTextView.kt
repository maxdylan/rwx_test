package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import com.example.lt.customviewtest.extension.px
import kotlin.random.Random

private val PADDING_HORI = 20f.px
private val PADDING_VER = 4f.px

class RandomColorTextView(context: Context?, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {
    private val colors = intArrayOf(Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        val index = Random.nextInt(5)
        color = colors[index]
        setPadding(
            PADDING_HORI.toInt(),
            PADDING_VER.toInt(),
            PADDING_HORI.toInt(),
            PADDING_VER.toInt()
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            PADDING_HORI,
            PADDING_HORI,
            paint
        )
        super.onDraw(canvas)
    }
}