package com.zt.android.oneday.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import com.zt.android.oneday.R
import kotlinx.android.synthetic.main.state_connected_view.view.*
import kotlinx.android.synthetic.main.state_normal_view.view.*


/**
 * created by ZT on 2020/1/16.
 */
class StateConnectedView : RelativeLayout {
    private val TAG = "StateConnectedView"

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.state_connected_view, this, true)
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

    fun startAn() {
        if (visibility == View.VISIBLE) {
            val a1 = ObjectAnimator.ofFloat(0.5f, 1f, 1f).apply {
                addUpdateListener {
                    (it.animatedValue as Float).apply {
                        iv_dot.scaleX = this
                        iv_dot.scaleY = this
                        iv_dot.alpha=this+0.1f
                    }
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
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
                        iv_dot.setImageResource(R.drawable.ic_conn_done2)
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
