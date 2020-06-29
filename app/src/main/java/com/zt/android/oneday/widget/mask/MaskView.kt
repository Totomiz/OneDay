package com.zt.android.oneday.widget.mask

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * created by ZT on 2020/3/6.
 */
class MaskView :View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val paint = Paint().apply {
        color = Color.parseColor("#25C0E9")
        maskFilter = BlurMaskFilter(40f, BlurMaskFilter.Blur.SOLID)
    }

    val paint2 = Paint().apply {
        color = Color.parseColor("#25C0E9")
        maskFilter = BlurMaskFilter(30f, BlurMaskFilter.Blur.INNER)
    }
    init {
        // 需禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }
//    val gradient=LinearGradient(1,11,1,1)


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cr=width/2f

        canvas.apply {
            save()
            translate(cr,cr)
            canvas.drawCircle(0f, 0f, dp2px(85f).toFloat(), paint)
            canvas.drawCircle(0f, 0f, dp2px(85f).toFloat(), paint2)

        }

//        canvas.translate(350f, 0f)
//        paint.maskFilter = BlurMaskFilter(100f, BlurMaskFilter.Blur.SOLID)
//        canvas.drawCircle(200f, 200f, 100f, paint)
//
//        canvas.translate(-350f, 350f)
//        paint.maskFilter = BlurMaskFilter(100f, BlurMaskFilter.Blur.NORMAL)
//        canvas.drawCircle(200f, 200f, 100f, paint)
//
//        canvas.translate(350f, 0f)
//        paint.maskFilter = BlurMaskFilter(100f, BlurMaskFilter.Blur.OUTER)
//        canvas.drawCircle(200f, 200f, 100f, paint)
//
//        canvas.translate(-350f, 350f)
//        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.SOLID)
//        canvas.drawBitmap(bmp, 200f,200f, paint)
    }

    fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}