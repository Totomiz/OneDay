package com.zt.android.oneday.widget.conn

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.free.vpn.proxy.shortcut.widget.conn2.IWaveStyle
import com.zt.android.oneday.R
import com.zt.android.utils.log.DebugLog
import kotlinx.android.synthetic.main.state_normal_view2.view.*

class StateNormalView : RelativeLayout {
    val TAG = "StateNormalView"

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var waveStyle: IWaveStyle = DefaultCircleWaveStylt(this)


    init {
        LayoutInflater.from(context).inflate(R.layout.state_normal_view2, this, true)
        setWillNotDraw(false)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        waveStyle.onFinishInflate()
        DebugLog.d(TAG, "onFinishInflate${measuredWidth}-${measuredHeight}-${width}-${height}-${iv_bg.width}-${iv_bg.height}")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        DebugLog.d(TAG, "onDraw${measuredWidth}-${measuredHeight}-${width}-${height}-${iv_bg.width}-${iv_bg.height}")
        canvas?.apply {
            waveStyle.draw(this, this@StateNormalView)
        }

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        DebugLog.d(TAG, w, h, oldw, oldh)
        DebugLog.d(TAG, "onSizeChanged${measuredWidth}-${measuredHeight}-${width}-${height}-${iv_bg.width}-${iv_bg.height}")
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        DebugLog.d(TAG, changed, l, t, r, b)
        waveStyle.onLayout(changed, l, t, r, b, iv_bg)
    }


    fun setWaveStyle(waveStyle: IWaveStyle) {
        this.waveStyle = waveStyle
        postInvalidate()
    }

    fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}