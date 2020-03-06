package com.zt.android.oneday.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.zt.android.oneday.R
import kotlinx.android.synthetic.main.state_normal_view.view.*

/**
 * created by ZT on 2020/1/16.
 */
class StateNormalView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        LayoutInflater.from(context).inflate(R.layout.state_normal_view, this, true)
//        requestDisallowInterceptTouchEvent(true)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
//        val aSet=AnimatorSet()
        iv_pre.scaleX = 1.44f
        iv_pre.scaleY = 1.44f
        val a1 = ValueAnimator.ofFloat(1f, 1.16f, 1f, 1.16f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f).apply {
            addUpdateListener {
                (it.animatedValue as Float).apply {
                    iv_bg.scaleX = this
                    iv_bg.scaleY = this
                }
            }
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            duration = 3000 // duration 3 seconds
            start()
        }

//        val a2 = ValueAnimator.ofFloat(1f, 1.16f,1f).apply {
//            addUpdateListener {
//                (it.animatedValue as Float).apply {
//                    iv_bg.scaleX = this
//                    iv_bg.scaleY = this
//                }
//            }
////            repeatMode = ValueAnimator.RESTART
////            repeatCount = ValueAnimator.INFINITE
//            duration = 500 // duration 3 seconds
////            start()
//        }

//
//        val a3 = ValueAnimator.ofFloat(1f,1f).apply {
//            addUpdateListener {
//                (it.animatedValue as Float).apply {
//                    iv_bg.scaleX = this
//                    iv_bg.scaleY = this
//                }
//            }
//
//            addListener(object :AnimatorListenerAdapter(){
//                override fun onAnimationEnd(animation: Animator?) {
//                    super.onAnimationEnd(animation)
//                    aSet.playSequentially(a1,a2,this@apply)
//                    duration=3000
//                    start()
//                }
//            })
////            repeatMode = ValueAnimator.RESTART
////            repeatCount = ValueAnimator.INFINITE
////            duration = 2000 // duration 3 seconds
////            start()
//        }


//       aSet.apply {
//            playSequentially(a1,a2,a3)
//            duration=3000
//            start()
//        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
