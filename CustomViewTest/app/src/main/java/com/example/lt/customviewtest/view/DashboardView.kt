package com.example.lt.customviewtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.lt.customviewtest.extension.px
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.sin

/**
 * 静态仪表盘
 *
 * @author tong
 */
class DashboardView : View {
    private var openAngle = 60f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val arcPath = Path()
    private val dashPath = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var arcRectF: RectF
    private lateinit var dashPathEffect: PathDashPathEffect

    private val angleOffset = (360f - openAngle) / 10f
    private val startAngle = 90f + openAngle / 2f

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f.px

        dashPath.addRect(0f, 0f, 3f.px, 10f.px, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        arcPath.reset()
        arcRectF =
            RectF(
                width / 2f - 150f.px,
                height / 2f - 150f.px,
                width / 2f + 150f.px,
                height / 2f + 150f.px
            )
        arcPath.addArc(arcRectF, 90f + openAngle / 2f, 360f - openAngle)
//        arcPath.addArc(,90f+startAngle,360f-startAngle*2)
        val pathMeasure = PathMeasure(arcPath, false)
        val offset = (pathMeasure.length - 3f.px) / 10
        dashPathEffect = PathDashPathEffect(dashPath, offset, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        // 先画外面的弧
        canvas.drawPath(arcPath, paint)
        // 再画刻度
        paint.pathEffect = dashPathEffect
        canvas.drawPath(arcPath, paint)
        paint.pathEffect = null
        // 最后画指针
        Log.d(
            "Dashboard",
            "angle: ${startAngle + angleOffset * 5}, startAngle: $startAngle, offset: $angleOffset"
        )
        Log.d(
            "Dashboard",
            "sin: ${sin(Math.toRadians((startAngle + angleOffset * 0).toDouble())).toFloat()}, cos: ${cos(
                Math.toRadians((startAngle + angleOffset * 0).toDouble())
            ).toFloat()}"
        )
        Log.d("Dashboard","radians: ${Math.toRadians((startAngle + angleOffset * 0).toDouble())}")
        canvas.drawLine(
            width / 2f, height / 2f,
            width / 2f + 100f.px * cos(Math.toRadians((startAngle + angleOffset * 8).toDouble()).toFloat()),
            height / 2f + 100f.px * sin(Math.toRadians((startAngle + angleOffset * 8).toDouble()).toFloat()),
            paint
        )

    }

}