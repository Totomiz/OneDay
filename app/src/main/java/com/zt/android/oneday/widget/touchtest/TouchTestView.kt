package com.zt.android.oneday.widget.touchtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

const val TAG_PRE = "Touch_test"

class TouchTestView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val TAG=TAG_PRE+"_TouchTestView"


    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.style = Paint.Style.FILL
            this.color = Color.parseColor("#FFA2A7")
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent() called with: event = $event")
        val superValue = super.dispatchTouchEvent(event)
        Log.d(TAG, "dispatchTouchEvent() called with:super.dispatchTouchEvent(event)=$superValue event = $event ")
        return superValue
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent() called with: event = $event")
        val value = super.onTouchEvent(event)
        Log.d(TAG, "onTouchEvent() called with:super.onTouchEvent(event)=$value event = $event ")
        return value
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            canvas.drawColor(Color.GRAY)
            this.translate(width / 2f, height / 2f)
            canvas.drawText("TouchTestView" + hashCode(), 0f, 0f, paint)
        }
    }
}