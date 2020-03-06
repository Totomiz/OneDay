package com.zt.android.oneday.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.zt.android.oneday.R
import kotlinx.android.synthetic.main.state_connecting_view.view.*

/**
 * created by ZT on 2020/1/16.
 */
class StateConnectingView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.state_connecting_view, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ValueAnimator.ofInt(0, 1, 2, 3).apply {
            addUpdateListener {
                iv_dot.setCurrent(it.animatedValue as Int)
                Log.d("ConnectingView", "onFinishInflate() called ${it.animatedValue as Int}")
            }

            duration = 1000 // duration 3 seconds
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
