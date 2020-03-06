package com.zt.android.oneday.widget.conn

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.zt.android.oneday.R
import kotlinx.android.synthetic.main.state_connecting_view2.view.*

/**
 * created by ZT on 2020/1/16.
 */
class StateConnectingView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.state_connecting_view2, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        geo_view.indicator.mPaint.style=Paint.Style.FILL
        geo_view.setIndicatorColor(Color.parseColor("#4EC4E9"))
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
