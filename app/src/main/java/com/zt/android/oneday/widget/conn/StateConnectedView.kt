package com.zt.android.oneday.widget.conn

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.os.SystemClock
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import com.zt.android.oneday.R
import com.zt.android.oneday.util.TimeUtil
import kotlinx.android.synthetic.main.state_connected_view.view.iv_dot
import kotlinx.android.synthetic.main.state_connected_view2.view.*


/**
 * created by ZT on 2020/1/16.
 */
class StateConnectedView : RelativeLayout {
    private val TAG = "StateConnectedView"

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.state_connected_view2, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }


    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            startAn()
        }

    }

    var startTimeBaseLine: Long = SystemClock.elapsedRealtime()
    var isConnected: Boolean = false


    fun setTimeBaseLine(isConnected: Boolean, baseTime: Long) {
        this.startTimeBaseLine = baseTime
        this.isConnected = isConnected
        startCalculateTime()
    }

    fun startCalculateTime() {
        tv_conn_time.text = TimeUtil.calculatTime(SystemClock.elapsedRealtime() - startTimeBaseLine)

        postDelayed({
            if (this.isConnected) startCalculateTime()
        }, 1000)
    }

    fun startAn() {
        if (visibility == View.VISIBLE) {
            val a1 = ObjectAnimator.ofFloat(0.5f, 1f, 1f).apply {
                addUpdateListener {
                    (it.animatedValue as Float).apply {
                        iv_dot.scaleX = this
                        iv_dot.scaleY = this
                        iv_dot.alpha = this + 0.1f
                    }
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        group_conn_info.visibility = View.GONE
                        iv_dot.visibility = View.VISIBLE
                        iv_dot.setImageResource(R.drawable.ic_conn_done)

                    }

                })

                duration = 1000 // duration 3 seconds
            }

            val a2 = ObjectAnimator.ofFloat(1.44f, 1.44f).apply {
                addUpdateListener {
                    (it.animatedValue as Float).apply {
                        iv_dot.scaleX = this
                        iv_dot.scaleY = this
                    }
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
//                        iv_dot.setImageResource(R.drawable.ic_conn_done2)
                        iv_dot.visibility = View.GONE
                        group_conn_info.visibility = View.VISIBLE

                    }

                })

                duration = 500 // duration 3 seconds
            }
            AnimatorSet().apply {
                playSequentially(a1, a2)
                setDuration(1500)
                start()
            }
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
